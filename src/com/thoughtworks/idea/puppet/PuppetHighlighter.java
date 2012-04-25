package com.thoughtworks.idea.puppet;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.thoughtworks.idea.puppet.parsing.PuppetLexer;
import com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition;
import com.thoughtworks.idea.puppet.psi.PuppetTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * @author Evgeny Dudin
 */
public class PuppetHighlighter extends SyntaxHighlighterBase implements SyntaxHighlighter {

    public static final TextAttributesKey ILLEGAL = createTextAttributesKey("PP_ILLEGAL", SyntaxHighlighterColors.INVALID_STRING_ESCAPE.getDefaultAttributes());
    public static final TextAttributesKey COMMENT = createTextAttributesKey("PP_COMMENT", SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes());
    public static final TextAttributesKey MODIFIER = createTextAttributesKey("PP_MODIFIER", SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
    public static final TextAttributesKey STRING = createTextAttributesKey("PP_STRING",//new TextAttributes(new Color(0x00, 0xff, 0), null, null, null, Font.BOLD));
            SyntaxHighlighterColors.STRING.getDefaultAttributes());
    public static final TextAttributesKey ARROW = createTextAttributesKey("PP_OP_PROPERTY", SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes());
    public static final TextAttributesKey BRACES = createTextAttributesKey("PP_BRACES", SyntaxHighlighterColors.BRACES.getDefaultAttributes());

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new PuppetLexer();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        if (type == TokenType.BAD_CHARACTER) {
            return pack(ILLEGAL);
        }
        if (type == PuppetParserDefinition.PUPPET_LINE_COMMENT || type == PuppetParserDefinition.PUPPET_BLOCK_COMMENT) {
            return pack(COMMENT);
        }
        if (type == PuppetTypes.PP_MODIFIER) {
            return pack(MODIFIER);
        }
        if (type == PuppetTypes.PP_STRING) {
            return pack(STRING);
        }
        if (type == PuppetTypes.PP_OP_PROPERTY) {
            return pack(ARROW);
        }

        if (type == PuppetTypes.PP_LEFT_BRACE || type == PuppetTypes.PP_RIGHT_BRACE) {
            return pack(BRACES);
        }
        return EMPTY;
    }

}
