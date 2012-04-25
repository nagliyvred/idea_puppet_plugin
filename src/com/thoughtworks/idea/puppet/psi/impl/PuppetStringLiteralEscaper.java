package com.thoughtworks.idea.puppet.psi.impl;

import com.intellij.openapi.util.ProperTextRange;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public class PuppetStringLiteralEscaper extends LiteralTextEscaper<PuppetStringImpl> {
    public PuppetStringLiteralEscaper(PuppetStringImpl element) {
        super(element);
    }

    @Override
    public boolean decode(@NotNull final TextRange rangeInsideHost, @NotNull final StringBuilder outChars) {
        // todo implement proper java-like string escapes support
        ProperTextRange.assertProperRange(rangeInsideHost);
        outChars.append(myHost.getText(), rangeInsideHost.getStartOffset(), rangeInsideHost.getEndOffset());
        return true;
    }

    @Override
    public int getOffsetInHost(final int offsetInDecoded, @NotNull final TextRange rangeInsideHost) {
        ProperTextRange.assertProperRange(rangeInsideHost);
        int offset = offsetInDecoded;
        // todo implement proper java-like string escapes support
        offset += rangeInsideHost.getStartOffset();
        if (offset < rangeInsideHost.getStartOffset()) offset = rangeInsideHost.getStartOffset();
        if (offset > rangeInsideHost.getEndOffset()) offset = rangeInsideHost.getEndOffset();
        return offset;
    }

    @Override
    public boolean isOneLine() {
        return true;
    }
}
