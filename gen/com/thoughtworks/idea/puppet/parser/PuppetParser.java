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
package com.thoughtworks.idea.puppet.parser;

import org.jetbrains.annotations.*;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static com.thoughtworks.idea.puppet.psi.PuppetTypes.*;
import static com.thoughtworks.idea.puppet.parser.PuppetParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class PuppetParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("com.thoughtworks.idea.puppet.parser.PuppetParser");

  @NotNull
  public ASTNode parse(final IElementType root_, final PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    if (root_ == PP_ATTR_NAME) {
      result_ = attr_name(builder_, level_ + 1);
    }
    else if (root_ == PP_ATTR_VALUE) {
      result_ = attr_value(builder_, level_ + 1);
    }
    else if (root_ == PP_ATTRIBUTE) {
      result_ = attribute(builder_, level_ + 1);
    }
    else if (root_ == PP_CLASS_BODY) {
      result_ = class_body(builder_, level_ + 1);
    }
    else if (root_ == PP_CLASS_DEFINITION) {
      result_ = class_definition(builder_, level_ + 1);
    }
    else if (root_ == PP_CLASS_NAME) {
      result_ = class_name(builder_, level_ + 1);
    }
    else if (root_ == PP_DEF_BODY) {
      result_ = def_body(builder_, level_ + 1);
    }
    else if (root_ == PP_EXPRESSION) {
      result_ = expression(builder_, level_ + 1);
    }
    else if (root_ == PP_INCLUSION) {
      result_ = inclusion(builder_, level_ + 1);
    }
    else if (root_ == PP_INSTANCE_NAME) {
      result_ = instance_name(builder_, level_ + 1);
    }
    else if (root_ == PP_MANIFEST) {
      result_ = manifest(builder_, level_ + 1);
    }
    else if (root_ == PP_MODIFIER) {
      result_ = modifier(builder_, level_ + 1);
    }
    else if (root_ == PP_OBJ_NAME) {
      result_ = obj_name(builder_, level_ + 1);
    }
    else if (root_ == PP_REFERENCE_OR_TOKEN) {
      result_ = reference_or_token(builder_, level_ + 1);
    }
    else if (root_ == PP_STRING_LITERAL_EXPRESSION) {
      result_ = string_literal_expression(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      result_ = recipe(builder_, level_ + 1);
      while (builder_.getTokenType() != null) {
        builder_.advanceLexer();
      }
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  private static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    TokenSet.create(PP_EXPRESSION, PP_REFERENCE_OR_TOKEN, PP_STRING_LITERAL_EXPRESSION),
  };
  public static boolean type_extends_(IElementType child_, IElementType parent_) {
    for (TokenSet set : EXTENDS_SETS_) {
      if (set.contains(child_) && set.contains(parent_)) return true;
    }
    return false;
  }

  /* ********************************************************** */
  // reference_or_token
  public static boolean attr_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attr_name")) return false;
    if (!nextTokenIs(builder_, PP_ID)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = reference_or_token(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_ATTR_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // (reference_or_token | string_literal_expression)
  public static boolean attr_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attr_value")) return false;
    return attr_value_0(builder_, level_ + 1);
  }

  // reference_or_token | string_literal_expression
  private static boolean attr_value_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attr_value_0")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = reference_or_token(builder_, level_ + 1);
    if (!result_) result_ = string_literal_expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_ATTR_VALUE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // attr_name '=>' attr_value ','*
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, PP_ID)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = attr_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PP_OP_PROPERTY);
    result_ = result_ && attr_value(builder_, level_ + 1);
    result_ = result_ && attribute_3(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_ATTRIBUTE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // ','*
  private static boolean attribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, PP_OP_COMMA)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "attribute_3");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // sequence attribute?
  public static boolean class_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_body")) return false;
    if (!nextTokenIs(builder_, PP_SEQUENCE)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PP_SEQUENCE);
    result_ = result_ && class_body_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_CLASS_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // attribute?
  private static boolean class_body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_body_1")) return false;
    attribute(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // modifier class_name '{' instance_name ':' class_body '}'
  public static boolean class_definition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_definition")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = modifier(builder_, level_ + 1);
    result_ = result_ && class_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PP_LEFT_BRACE);
    result_ = result_ && instance_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ":");
    result_ = result_ && class_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PP_RIGHT_BRACE);
    if (result_) {
      marker_.done(PP_CLASS_DEFINITION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // reference_or_token
  public static boolean class_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_name")) return false;
    if (!nextTokenIs(builder_, PP_ID)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = reference_or_token(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_CLASS_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // inclusion | class_definition
  public static boolean def_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "def_body")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = inclusion(builder_, level_ + 1);
    if (!result_) result_ = class_definition(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_DEF_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // sequence choice?
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    if (!nextTokenIs(builder_, PP_SEQUENCE)) return false;
    boolean result_ = false;
    final int start_ = builder_.getCurrentOffset();
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PP_SEQUENCE);
    result_ = result_ && expression_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), PP_EXPRESSION)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(PP_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // choice?
  private static boolean expression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_1")) return false;
    consumeToken(builder_, PP_CHOICE);
    return true;
  }

  /* ********************************************************** */
  // 'include' class_name
  public static boolean inclusion(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inclusion")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, "include");
    result_ = result_ && class_name(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_INCLUSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // string_literal_expression
  public static boolean instance_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "instance_name")) return false;
    if (!nextTokenIs(builder_, PP_STRING)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = string_literal_expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_INSTANCE_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // modifier obj_name '{' def_body* '}'
  public static boolean manifest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "manifest")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = modifier(builder_, level_ + 1);
    result_ = result_ && obj_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PP_LEFT_BRACE);
    result_ = result_ && manifest_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PP_RIGHT_BRACE);
    if (result_) {
      marker_.done(PP_MANIFEST);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // def_body*
  private static boolean manifest_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "manifest_3")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!def_body(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "manifest_3");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // 'node' | 'class' | 'define' | 'inherits' | 'contained'
  public static boolean modifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "modifier")) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, "node");
    if (!result_) result_ = consumeToken(builder_, "class");
    if (!result_) result_ = consumeToken(builder_, "define");
    if (!result_) result_ = consumeToken(builder_, "inherits");
    if (!result_) result_ = consumeToken(builder_, "contained");
    if (result_) {
      marker_.done(PP_MODIFIER);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // reference_or_token
  public static boolean obj_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "obj_name")) return false;
    if (!nextTokenIs(builder_, PP_ID)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = reference_or_token(builder_, level_ + 1);
    if (result_) {
      marker_.done(PP_OBJ_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // sequence manifest?
  static boolean recipe(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recipe")) return false;
    if (!nextTokenIs(builder_, PP_SEQUENCE)) return false;
    boolean result_ = false;
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PP_SEQUENCE);
    result_ = result_ && recipe_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // manifest?
  private static boolean recipe_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recipe_1")) return false;
    manifest(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // id
  public static boolean reference_or_token(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "reference_or_token")) return false;
    if (!nextTokenIs(builder_, PP_ID)) return false;
    boolean result_ = false;
    final int start_ = builder_.getCurrentOffset();
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PP_ID);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), PP_REFERENCE_OR_TOKEN)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(PP_REFERENCE_OR_TOKEN);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // string
  public static boolean string_literal_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_literal_expression")) return false;
    if (!nextTokenIs(builder_, PP_STRING)) return false;
    boolean result_ = false;
    final int start_ = builder_.getCurrentOffset();
    final Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, PP_STRING);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), PP_STRING_LITERAL_EXPRESSION)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(PP_STRING_LITERAL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

}
