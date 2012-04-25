package com.thoughtworks.idea.puppet;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition;

/**
 * @author Evgeny Dudin
 */
public class PuppetCommenter implements CodeDocumentationAwareCommenter {
    @Override
    public IElementType getLineCommentTokenType() {
        return PuppetParserDefinition.PUPPET_LINE_COMMENT;
    }

    @Override
    public IElementType getBlockCommentTokenType() {
        return PuppetParserDefinition.PUPPET_BLOCK_COMMENT;
    }

    @Override
    public IElementType getDocumentationCommentTokenType() {
        return null;
    }

    @Override
    public String getDocumentationCommentPrefix() {
        return null;
    }

    @Override
    public String getDocumentationCommentLinePrefix() {
        return null;
    }

    @Override
    public String getDocumentationCommentSuffix() {
        return null;
    }

    @Override
    public boolean isDocumentationComment(PsiComment psiComment) {
        return false;
    }

    @Override
    public String getLineCommentPrefix() {
        return "#";
    }

    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
