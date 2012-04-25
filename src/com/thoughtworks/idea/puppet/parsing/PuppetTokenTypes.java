package com.thoughtworks.idea.puppet.parsing;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author Evgeny Dudin
 */
public interface PuppetTokenTypes {
    
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    IElementType END_OF_LINE_COMMENT = new PuppetElementType("END_OF_LINE_COMMENT");

    IElementType PUPPET_DEFINE = new PuppetElementType("PUPPET_DEFINE");
    IElementType PUPPET_NODE   = new PuppetElementType("PUPPET_NODE");
    IElementType PUPPET_CLASS  = new PuppetElementType("PUPPET_CLASS");
    IElementType PUPPET_ATTR_KEY = new PuppetElementType("PUPPET_ATTR_KEY");
    IElementType PUPPET_ATTR_VALUE = new PuppetElementType("PUPPET_ATTR_VALUE");
    IElementType PUPPET_TYPE = new PuppetElementType("PUPPET_TYPE");

    IElementType PUPPET_BRACE  = TokenType.CODE_FRAGMENT;


    TokenSet COMMENTS = TokenSet.create(END_OF_LINE_COMMENT);
    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
}
