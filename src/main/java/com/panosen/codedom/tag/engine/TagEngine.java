package com.panosen.codedom.tag.engine;

import com.google.common.base.Strings;
import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.tag.Tag;

import java.util.List;
import java.util.Map;

public class TagEngine {

    public void generate(Tag tag, CodeWriter codeWriter, GenerateOptions options) {
        if (tag == null) {
            return;
        }
        if (codeWriter == null) {
            return;
        }

        //在节点前面插入空行
        if (tag.isMargin() || tag.isMarginTop()) {
            codeWriter.writeLine();
        }

        //注释
        if (!Strings.isNullOrEmpty(tag.getComment())) {
            codeWriter.write(options.getIndentString()).write(Marks.LESS_THAN).write(Marks.Exclamation).write(Marks.MINUS).write(Marks.MINUS).write(Marks.WHITESPACE);

            codeWriter.write(tag.getComment());

            codeWriter.write(Marks.WHITESPACE).write(Marks.MINUS).write(Marks.MINUS).writeLine(Marks.GREATER_THAN);
        }

        codeWriter.write(options.getIndentString()).write(Marks.LESS_THAN).write(tag.getName());

        GenerateProperties(tag, codeWriter, options);

        GenerateAttributes(tag, codeWriter, options);

        boolean noChildren = tag.getChildren() == null || tag.getChildren().isEmpty();
        boolean noContent = Strings.isNullOrEmpty(tag.getContent());
        //自闭和标签 <input /> <hr />
        if (tag.isSelfClosing() && noChildren && noContent) {

            codeWriter.write(Marks.WHITESPACE).write(Marks.SLASH).write(Marks.GREATER_THAN);

            //在节点后面插入空行
            if (tag.isMargin() || tag.isMarginBottom()) {
                codeWriter.writeLine();
            }

            return;
        }

        codeWriter.write(Marks.GREATER_THAN);

        //<子节点>
        {
            if (tag.getChildren() != null && !tag.getChildren().isEmpty()) {
                if (tag.isPadding() || tag.isPaddingTop()) {
                    codeWriter.writeLine();
                }
            }

            GenerateChildren(tag.getChildren(), codeWriter, options);

            if (tag.getChildren() != null && !tag.getChildren().isEmpty()) {
                if (tag.isPadding() || tag.isPaddingBottom()) {
                    codeWriter.writeLine();
                }
            }
        }

        //<内容>
        if (!Strings.isNullOrEmpty(tag.getContent())) {
            codeWriter.write(tag.getContent());
        }

        if (tag.getChildren() != null && !tag.getChildren().isEmpty()) {
            codeWriter.writeLine();
            codeWriter.write(options.getIndentString());
        }

        codeWriter.write(Marks.LESS_THAN).write(Marks.SLASH).write(tag.getName()).write(Marks.GREATER_THAN);

        //在节点后面插入空行
        if (tag.isMargin() || tag.isMarginBottom()) {
            codeWriter.writeLine();
        }
    }

    private void GenerateChildren(List<Tag> tags, CodeWriter codeWriter, GenerateOptions options) {
        if (tags == null || tags.isEmpty()) {
            return;
        }

        options.pushIndent();
        for (Tag tag : tags) {
            codeWriter.writeLine();

            generate(tag, codeWriter, options);
        }

        options.popIndent();
    }

    private void GenerateAttributes(Tag tag, CodeWriter codeWriter, GenerateOptions options) {
        if (tag.getAttributeSet() == null || tag.getAttributeSet().isEmpty()) {
            return;
        }

        options.pushIndent();
        options.pushIndent();

        for (String attribute : tag.getAttributeSet()) {
            if (tag.isWrapProperty()) {
                codeWriter.writeLine();
                codeWriter.write(options.getIndentString());
            } else {
                codeWriter.write(Marks.WHITESPACE);
            }
            codeWriter.write(attribute);
        }

        options.popIndent();
        options.popIndent();
    }

    private void GenerateProperties(Tag tag, CodeWriter codeWriter, GenerateOptions options) {
        if (tag.getPropertyMap() == null || tag.getPropertyMap().isEmpty()) {
            return;
        }

        options.pushIndent();
        options.pushIndent();

        for (Map.Entry<String, String> property : tag.getPropertyMap().entrySet()) {
            if (tag.isWrapProperty()) {
                codeWriter.writeLine();
                codeWriter.write(options.getIndentString());
            } else {
                codeWriter.write(Marks.WHITESPACE);
            }
            codeWriter.write(nullOrDefault(property.getKey(), "")).write(Marks.EQUAL)
                    .write(Marks.DOUBLE_QUOTATION).write(nullOrDefault(property.getValue(), "")).write(Marks.DOUBLE_QUOTATION);
        }

        options.popIndent();
        options.popIndent();
    }

    private <T> T nullOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
}
