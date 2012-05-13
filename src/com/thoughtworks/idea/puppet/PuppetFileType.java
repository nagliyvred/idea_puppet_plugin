package com.thoughtworks.idea.puppet;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Evgeny Dudin
 */
public class PuppetFileType extends LanguageFileType {

    public static final Icon FILE_ICON = IconLoader.getIcon("/icons/puppet-icon.png");
    public static final LanguageFileType INSTANCE = new PuppetFileType();
    @NonNls public static final String DEFAULT_EXTENSION = "pp";
    @NonNls public static final String DOT_DEFAULT_EXTENSION = "."+DEFAULT_EXTENSION;

    protected PuppetFileType() {
        super(PuppetLanguage.INSTANCE);
    }


    @NotNull
    @Override
    public String getName() {
        return "Puppet";
    }

    @NotNull
    @Override
    public String getDescription() {
        return PropertiesBundle.message("puppet.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    public Icon getIcon() {
        return FILE_ICON;
    }
}
