package com.thoughtworks.idea.puppet.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.thoughtworks.idea.puppet.psi.PuppetCompositeElement;

/**
 * @author Evgeny Dudin
 */
public class PuppetCompositeElementImpl extends ASTWrapperPsiElement implements PuppetCompositeElement{

    public PuppetCompositeElementImpl(@org.jetbrains.annotations.NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getNode().getElementType().toString();
    }
}
