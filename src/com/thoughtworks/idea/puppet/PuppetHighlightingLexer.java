package com.thoughtworks.idea.puppet;

import com.intellij.lexer.LayeredLexer;
import com.thoughtworks.idea.puppet.parsing.PuppetLexer;

/**
 * @author Evgeny Dudin
 */
public class PuppetHighlightingLexer extends LayeredLexer {

    public PuppetHighlightingLexer() {
        super(new PuppetLexer());
//        registerSelfStoppingLayer(
//                new StringLiteralLexer(StringLiteralLexer.NO_QUOTE_CHAR,
//                                        PuppetTokenTypes.VALUE_CHARACTERS, true, "#!=:"),
//                new IElementType[]{PuppetTokenTypes.VALUE_CHARACTERS},
//                IElementType.EMPTY_ARRAY);
//        registerSelfStoppingLayer(new StringLiteralLexer(StringLiteralLexer.NO_QUOTE_CHAR,
//                                        PuppetTokenTypes.KEY_CHARACTERS, true, "#!=: "),
//                new IElementType[]{PuppetTokenTypes.KEY_CHARACTERS},
//                IElementType.EMPTY_ARRAY);
    }
}
