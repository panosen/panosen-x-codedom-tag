package com.panosen.codedom.tag.engine;

/*
 *------------------------------------------------------------------------------
 *     Do not go gentle into that goods night.
 *
 *     harriszhang@live.cn
 *------------------------------------------------------------------------------
 */

public class GenerateOptions {

    public GenerateOptions() {
        this.tabString = "    ";
        this.indentSize = 0;
        this.indentString = "";
    }

    /**
     * TabString
     */
    private String tabString;

    /**
     * 缩进个数
     */
    private Integer indentSize;

    /**
     * 缩进字符
     */
    private String indentString;

    /**
     * [getter] TabString
     *
     * @return String
     */
    public String getTabString() {
        return tabString;
    }

    /**
     * [setter] TabString
     *
     * @param tabString tabString
     */
    public void setTabString(String tabString) {
        this.tabString = tabString;
    }

    /**
     * [getter] 缩进个数
     *
     * @return Integer
     */
    public Integer getIndentSize() {
        return indentSize;
    }

    /**
     * [setter] 缩进个数
     *
     * @param indentSize indentSize
     */
    public void setIndentSize(Integer indentSize) {
        this.indentSize = indentSize;

        buildIndentString();
    }

    /**
     * [getter] 缩进字符
     *
     * @return String
     */
    public String getIndentString() {
        return indentString;
    }

    /**
     * 增加缩进
     */
    public void pushIndent() {
        this.indentSize = this.indentSize + 1;
        buildIndentString();
    }

    /**
     * 减少缩进
     */
    public void popIndent() {
        if (this.indentSize > 0) {
            this.indentSize = this.indentSize - 1;
        }
        buildIndentString();
    }

    private void buildIndentString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.indentSize; i++) {
            builder.append(this.tabString);
        }
        this.indentString = builder.toString();
    }
}
