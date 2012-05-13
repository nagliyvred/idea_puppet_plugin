package com.thoughtworks.idea.puppet;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.thoughtworks.idea.puppet.parsing.PuppetLexer;
import com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition;
import com.thoughtworks.idea.puppet.psi.PuppetTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

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
    public static final TextAttributesKey VARIABLE = createTextAttributesKey("PP_VARIABLE",
            new TextAttributes(new Color(0x94, 0x1b, 0xE0), null, null, null, Font.BOLD));
    public static final TextAttributesKey DEPENDENCY = createTextAttributesKey("PP_DEPENDENCY",
            new TextAttributes(new Color(224, 147, 52), null, null, null, Font.BOLD));
    public static final TextAttributesKey DIGITS = createTextAttributesKey("PP_DIGITS",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes());
    public static final TextAttributesKey FUNCTION = createTextAttributesKey("PP_FUNCTION",
            new TextAttributes(new Color(88, 161, 224), null, null, null, Font.ITALIC));

    public static final TextAttributesKey VIRTUAL = createTextAttributesKey("PP_VIRTUAL",
            new TextAttributes(new Color(224, 10, 32), null, null, null, Font.BOLD));


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
        if (type == PuppetTypes.PP_DEPENDENCY) {
            return pack(DEPENDENCY);
        }
        if (type == PuppetTypes.PP_FUNCTION) {
            return pack(FUNCTION);
        }
        if (type == PuppetTypes.PP_VIRTUAL) {
            return pack(VIRTUAL);
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
        if (type == PuppetTypes.PP_VARIABLE) {
            return pack(VARIABLE);
        }
        if (type == PuppetTypes.PP_DIGITS) {
            return pack(DIGITS);
        }

        if (type == PuppetTypes.PP_LEFT_BRACE || type == PuppetTypes.PP_RIGHT_BRACE) {
            return pack(BRACES);
        }
        return EMPTY;
    }

}
