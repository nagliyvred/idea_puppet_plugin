/*
 * Copyright 2012-2012 ThoughtWorks Australia Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thoughtworks.idea.puppet.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import static com.thoughtworks.idea.puppet.psi.PuppetTypes.*;
import com.thoughtworks.idea.puppet.psi.*;

public class PuppetManifestImpl extends PuppetCompositeElementImpl implements PuppetManifest {

  public PuppetManifestImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<PuppetDefBody> getDefBodyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PuppetDefBody.class);
  }

  @Override
  @NotNull
  public PuppetModifier getModifier() {
    return findNotNullChildByClass(PuppetModifier.class);
  }

  @Override
  @NotNull
  public PuppetObjName getObjName() {
    return findNotNullChildByClass(PuppetObjName.class);
  }

}
