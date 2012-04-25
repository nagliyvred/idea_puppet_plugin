package com.thoughtworks.idea.puppet.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.thoughtworks.idea.puppet.psi.PuppetReferenceOrToken;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public class PuppetRefOrTokenImpl extends PuppetExpressionImpl implements PuppetReferenceOrToken {

    public PuppetRefOrTokenImpl(ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
//        return new PuppetReferenceImpl<PuppetReferenceOrToken>(this, TextRange.from(0, getTextLength())) {
//            @Override
//            public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
//                myElement.getId().replace(BnfElementFactory.createLeafFromText(getElement().getProject(), newElementName));
//                return myElement;
//            }
//        };
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getText();
    }

    @NotNull
    @Override
    public PsiElement getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
