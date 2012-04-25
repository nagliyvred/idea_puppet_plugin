package com.thoughtworks.idea.puppet.parsing;

import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;

/**
 * @author Evgeny Dudin
 */
public class PuppetElementType extends IElementType {
    public PuppetElementType(@NonNls String debugName) {
        super(debugName, StdFileTypes.PLAIN_TEXT.getLanguage());
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Puppet:" + super.toString();
    }
}
