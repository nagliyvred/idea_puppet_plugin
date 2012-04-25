package com.thoughtworks.idea.puppet.parsing;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.thoughtworks.idea.puppet.parser._PuppetLexer;

/**
 * @author Evgeny Dudin
 */
public class PuppetLexer extends LookAheadLexer {

    public PuppetLexer() {
        super(new MergingLexerAdapter(new FlexAdapter(new _PuppetLexer()), PuppetParserDefinition.COMMENTS));
    }

    @Override
    protected void lookAhead(Lexer baseLexer) {
        super.lookAhead(baseLexer);
    }
}
