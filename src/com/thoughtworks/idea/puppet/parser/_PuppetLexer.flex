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

ALPHA=[:letter:]
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

Digits={DIGIT} *

ID_BODY={ALPHA} | {DIGIT} | "_" | "::"
ID_END={ALPHA} | {DIGIT}
ID={ALPHA} ({ID_BODY}) * {ID_END}
Variable="$" {ID}

Dependency= "File" | "Exec" | "Service" | "Yumrepo" | "Class"
//"[" {AnySpace} {STRING} {AnySpace} "]"

//ClassDefinition="class" {AnySpace} * {ID} {AnySpace} * "{"
//ClassInvokation="class" {AnySpace} * "{" {STRING} ":"
//TypeDefinition={ID} {AnySpace} * "{"
Virtual="@" {ID}

%%
<YYINITIAL> {

    {AnySpace}+             { yybegin(YYINITIAL); return com.intellij.psi.TokenType.WHITE_SPACE; }
    {Comment}               { yybegin(YYINITIAL); return PUPPET_LINE_COMMENT; }
    {STRING}                { yybegin(YYINITIAL); return PuppetTypes.PP_STRING; }
    {BAD_TOKENS}            { yybegin(YYINITIAL); return com.intellij.psi.TokenType.BAD_CHARACTER; }
    "class"                 { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "define"                { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "node"                  { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "package"               { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "file"                  { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "exec"                  { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "service"               { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "include"               { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "import"                { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "inherits"              { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "if"                    { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "else"                  { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "realize"               { yybegin(YYINITIAL); return PuppetTypes.PP_MODIFIER; }
    "template"              { yybegin(YYINITIAL); return PuppetTypes.PP_FUNCTION; }
    "{"                     { yybegin(YYINITIAL); return PuppetTypes.PP_LEFT_BRACE; }
    "("                     { yybegin(YYINITIAL); return PuppetTypes.PP_LEFT_BRACE; }
    "}"                     { yybegin(YYINITIAL); return PuppetTypes.PP_RIGHT_BRACE; }
    ")"                     { yybegin(YYINITIAL); return PuppetTypes.PP_RIGHT_BRACE; }
    "File"                  { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "Exec"                  { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "Service"               { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "Yumrepo"               { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "Class"                 { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "Package"               { yybegin(YYINITIAL); return PuppetTypes.PP_DEPENDENCY; }
    "=>"                    { yybegin(YYINITIAL); return PuppetTypes.PP_OP_PROPERTY; }
    ","                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    "["                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    "]"                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    ":"                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    "="                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    ";"                     { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    "->"                    { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    "+>"                    { yybegin(YYINITIAL); return PuppetTypes.PP_OP_COMMA; }
    {Virtual}               { yybegin(YYINITIAL); return PuppetTypes.PP_VIRTUAL; }
    {Variable}              { yybegin(YYINITIAL); return PuppetTypes.PP_VARIABLE; }
    {ID}                    { yybegin(YYINITIAL); return PuppetTypes.PP_CLASS_NAME; }
    {Digits}                { yybegin(YYINITIAL); return PuppetTypes.PP_DIGITS; }
    .                       { yybegin(YYINITIAL); return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

