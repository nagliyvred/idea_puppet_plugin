package com.thoughtworks.idea.puppet.parser;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import com.thoughtworks.idea.puppet.psi.PuppetTypes;
import static com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition.PUPPET_LINE_COMMENT;
import static com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition.PUPPET_BLOCK_COMMENT;

%%

%{
  public _PuppetLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _PuppetLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%eof{ return;
%eof}

DIGIT=[:digit:]

LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t]
AnySpace = {LineTerminator} | {WhiteSpace} | [\f]

LINE_COMMENT="#" .*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?
Comment={LINE_COMMENT} | {BLOCK_COMMENT}

HEX={DIGIT} | [aAbBcCdDeEfF]

ESC="\\" ( [^] | "u" {HEX}{HEX}{HEX}{HEX} )
CHAR={ESC} | [^\r\n\'\"\\]
STRING_BAD1=\" ({CHAR} | \') *
STRING_BAD2=\' {CHAR} *
STRING={STRING_BAD1} \" | {STRING_BAD2} \'

BAD_TOKENS={STRING_BAD1} | {STRING_BAD2}

%%
<YYINITIAL> {

    {AnySpace}+             { yybegin(YYINITIAL); return com.intellij.psi.TokenType.WHITE_SPACE; }
    {Comment}               { yybegin(YYINITIAL); return PUPPET_LINE_COMMENT; }
    {STRING}                { yybegin(YYINITIAL); return PuppetTypes.PP_STRING; }
    {BAD_TOKENS}            { yybegin(YYINITIAL); return com.intellij.psi.TokenType.BAD_CHARACTER; }
    // do not want to highlight all in red...
    .                       { yybegin(YYINITIAL); return com.intellij.psi.TokenType.WHITE_SPACE; }
//    [^]                     { yybegin(YYINITIAL); return com.intellij.psi.TokenType.BAD_CHARACTER; }
//    .                       { yybegin(YYINITIAL); return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

