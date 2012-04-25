package com.thoughtworks.idea.puppet.psi;

import com.intellij.psi.tree.IElementType;
import com.thoughtworks.idea.puppet.PuppetLanguage;

/**
 * @author Evgeny Dudin
 */
public class PuppetCompositeElementType extends IElementType{

    public PuppetCompositeElementType(@org.jetbrains.annotations.NotNull @org.jetbrains.annotations.NonNls String debugName) {
        super(debugName, PuppetLanguage.INSTANCE);
    }
}
