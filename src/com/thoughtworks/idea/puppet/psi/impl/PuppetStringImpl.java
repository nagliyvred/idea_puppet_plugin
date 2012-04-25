package com.thoughtworks.idea.puppet.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.PsiReference;
import com.thoughtworks.idea.puppet.psi.PuppetStringLiteralExpression;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public abstract class PuppetStringImpl extends PuppetExpressionImpl implements PuppetStringLiteralExpression, PsiLanguageInjectionHost {


    public PuppetStringImpl(ASTNode node) {
        super(node);
    }

//    @Override
//    public PsiElement getNumber() {
//        return null;
//    }

    @Override
    public PsiReference getReference() {
//        if (!(getParent() instanceof PuppetAttrValue)) return null;
//        return new PuppetReferenceImpl<PuppetStringLiteralExpression>(this, TextRange.from(1, getTextLength() - 2)) {
//            @Override
//            public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
//                return getString().replace(PuppetElementFactory.createLeafFromText(getProject(), '\"' + newElementName + '\"'));
//            }
//        };
        return null;
    }

    @Override
    public boolean isValidHost() {
        return true;
    }

    @Override
    public PuppetStringImpl updateText(@NotNull final String text) {
//        final PuppetExpression expression = PuppetElementFactory.createExpressionFromText(getProject(), text);
//        assert expression instanceof PuppetStringImpl : text + "-->" + expression;
//        return (PuppetStringImpl)this.replace(expression);
        return this;
    }

    @NotNull
    @Override
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return new PuppetStringLiteralEscaper(this);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getText();
    }
}
