package com.thoughtworks.idea.puppet;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public class PuppetLanguage extends Language {
    
    public static final PuppetLanguage INSTANCE = new PuppetLanguage();
    
    protected PuppetLanguage() {
        super("Puppet", "text/plain");
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            protected SyntaxHighlighter createHighlighter() {
                return new PuppetHighlighter();
            }
        });

    }
}
