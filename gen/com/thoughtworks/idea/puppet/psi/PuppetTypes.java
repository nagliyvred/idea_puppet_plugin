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
package com.thoughtworks.idea.puppet.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.thoughtworks.idea.puppet.psi.impl.*;

public interface PuppetTypes {

  IElementType PP_ATTR_NAME = new PuppetCompositeElementType("PP_ATTR_NAME");
  IElementType PP_ATTR_VALUE = new PuppetCompositeElementType("PP_ATTR_VALUE");
  IElementType PP_ATTRIBUTE = new PuppetCompositeElementType("PP_ATTRIBUTE");
  IElementType PP_CLASS_BODY = new PuppetCompositeElementType("PP_CLASS_BODY");
  IElementType PP_CLASS_DEFINITION = new PuppetCompositeElementType("PP_CLASS_DEFINITION");
  IElementType PP_CLASS_NAME = new PuppetCompositeElementType("PP_CLASS_NAME");
  IElementType PP_DEF_BODY = new PuppetCompositeElementType("PP_DEF_BODY");
  IElementType PP_DEPENDENCY = new PuppetCompositeElementType("PP_DEPENDENCY");
  IElementType PP_DIGITS = new PuppetCompositeElementType("PP_DIGITS");
  IElementType PP_EXPRESSION = new PuppetCompositeElementType("PP_EXPRESSION");
  IElementType PP_INCLUSION = new PuppetCompositeElementType("PP_INCLUSION");
  IElementType PP_INSTANCE_NAME = new PuppetCompositeElementType("PP_INSTANCE_NAME");
  IElementType PP_MANIFEST = new PuppetCompositeElementType("PP_MANIFEST");
  IElementType PP_MODIFIER = new PuppetCompositeElementType("PP_MODIFIER");
  IElementType PP_OBJ_NAME = new PuppetCompositeElementType("PP_OBJ_NAME");
  IElementType PP_REFERENCE_OR_TOKEN = new PuppetCompositeElementType("PP_REFERENCE_OR_TOKEN");
  IElementType PP_STRING_LITERAL_EXPRESSION = new PuppetCompositeElementType("PP_STRING_LITERAL_EXPRESSION");
  IElementType PP_VARIABLE = new PuppetCompositeElementType("PP_VARIABLE");

  IElementType PP_CHOICE = new PuppetTokenType("choice");
  IElementType PP_DIGIT = new PuppetTokenType("digit");
  IElementType PP_FUNCTION = new PuppetTokenType("function");
  IElementType PP_ID = new PuppetTokenType("id");
  IElementType PP_LEFT_BRACE = new PuppetTokenType("{");
  IElementType PP_OP_COMMA = new PuppetTokenType(",");
  IElementType PP_OP_PROPERTY = new PuppetTokenType("=>");
  IElementType PP_RIGHT_BRACE = new PuppetTokenType("}");
  IElementType PP_SEQUENCE = new PuppetTokenType("sequence");
  IElementType PP_STRING = new PuppetTokenType("string");
  IElementType PP_VIRTUAL = new PuppetTokenType("virtual");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PP_ATTR_NAME) {
        return new PuppetAttrNameImpl(node);
      }
      else if (type == PP_ATTR_VALUE) {
        return new PuppetAttrValueImpl(node);
      }
      else if (type == PP_ATTRIBUTE) {
        return new PuppetAttributeImpl(node);
      }
      else if (type == PP_CLASS_BODY) {
        return new PuppetClassBodyImpl(node);
      }
      else if (type == PP_CLASS_DEFINITION) {
        return new PuppetClassDefinitionImpl(node);
      }
      else if (type == PP_CLASS_NAME) {
        return new PuppetClassNameImpl(node);
      }
      else if (type == PP_DEF_BODY) {
        return new PuppetDefBodyImpl(node);
      }
      else if (type == PP_DEPENDENCY) {
        return new PuppetDependencyImpl(node);
      }
      else if (type == PP_DIGITS) {
        return new PuppetDigitsImpl(node);
      }
      else if (type == PP_EXPRESSION) {
        return new PuppetExpressionImpl(node);
      }
      else if (type == PP_INCLUSION) {
        return new PuppetInclusionImpl(node);
      }
      else if (type == PP_INSTANCE_NAME) {
        return new PuppetInstanceNameImpl(node);
      }
      else if (type == PP_MANIFEST) {
        return new PuppetManifestImpl(node);
      }
      else if (type == PP_MODIFIER) {
        return new PuppetModifierImpl(node);
      }
      else if (type == PP_OBJ_NAME) {
        return new PuppetObjNameImpl(node);
      }
      else if (type == PP_REFERENCE_OR_TOKEN) {
        return new PuppetReferenceOrTokenImpl(node);
      }
      else if (type == PP_STRING_LITERAL_EXPRESSION) {
        return new PuppetStringLiteralExpressionImpl(node);
      }
      else if (type == PP_VARIABLE) {
        return new PuppetVariableImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
