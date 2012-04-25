package com.thoughtworks.idea.puppet.parsing;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.thoughtworks.idea.puppet.PuppetLanguage;
import com.thoughtworks.idea.puppet.psi.PuppetTokenType;
import com.thoughtworks.idea.puppet.psi.PuppetTypes;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public class PuppetParserDefinition implements ParserDefinition {

    private static final Logger LOG = Logger.getInstance("#com.thoughtworks.idea.puppet.parsing.PuppetParserDefinition");
    public static final IFileElementType PUPPET_FILE_ELEMENT_TYPE = new IFileElementType("PUPPET_FILE", PuppetLanguage.INSTANCE);
    public static final IElementType PUPPET_LINE_COMMENT = new PuppetTokenType("PP_LINE_COMMENT");
    public static final IElementType PUPPET_BLOCK_COMMENT = new PuppetTokenType("PP_BLOCK_COMMENT");
//    public static final IElementType PP_LEFT_BRACE = new PuppetTokenType("PP_LEFT_BRACE");
//    public static final IElementType PP_RIGHT_BRACE = new PuppetTokenType("PP_RIGHT_BRACE");
    public static final TokenSet WS = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(PUPPET_LINE_COMMENT, PUPPET_BLOCK_COMMENT);
//    public static final IElementType MODIFIER = new PuppetCompositeElementType("PP_MODIFIER");
//    public static final IElementType STRING = new PuppetTokenType("PP_STRING");
    public static final TokenSet LITERALS = TokenSet.create(PuppetTypes.PP_STRING);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new PuppetLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new com.thoughtworks.idea.puppet.parser.PuppetParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return PUPPET_FILE_ELEMENT_TYPE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WS;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode astNode) {
        return PuppetTypes.Factory.createElement(astNode);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new PuppetFileImpl(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return SpaceRequirements.MAY;
    }
}
