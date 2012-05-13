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

public class PuppetAttrValueImpl extends PuppetCompositeElementImpl implements PuppetAttrValue {

  public PuppetAttrValueImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public PuppetDependency getDependency() {
    return findChildByClass(PuppetDependency.class);
  }

  @Override
  @Nullable
  public PuppetDigits getDigits() {
    return findChildByClass(PuppetDigits.class);
  }

  @Override
  @Nullable
  public PuppetExpression getExpression() {
    return findChildByClass(PuppetExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getFunction() {
    return findChildByType(PP_FUNCTION);
  }

}
