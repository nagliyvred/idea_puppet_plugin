package com.thoughtworks.idea.puppet.parsing;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.thoughtworks.idea.puppet.PuppetFileType;
import com.thoughtworks.idea.puppet.PuppetLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny Dudin
 */
public class PuppetFileImpl extends PsiFileBase implements PsiFile {

    public PuppetFileImpl(FileViewProvider fileViewProvider) {
        super(fileViewProvider, PuppetLanguage.INSTANCE);
    }


    @NotNull
    @Override
    public FileType getFileType() {
        return PuppetFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Puppet recipe: " + getName();
    }
}
