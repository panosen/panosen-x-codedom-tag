package com.panosen.codedom.tag;

import com.google.common.collect.Lists;

import java.util.*;

public class Tag {

    private String name;

    private String comment;

    private Map<String, String> propertyMap;

    private Set<String> attributeSet;

    /**
     * 内容
     */
    private String content;

    /**
     * 子节点
     */
    private List<Tag> children;


    /**
     * 在前面(外)和后面(外)插入空行
     */
    private boolean margin;

    /**
     * 在前面(外)插入空行
     */
    private boolean marginTop;

    /**
     * 在后面(外)插入空行
     */
    private boolean marginBottom;

    /**
     * 在前面(内)和后面(内)插入空行
     */
    private boolean padding;

    /**
     * 在前面(内)插入空行
     */
    private boolean paddingTop;

    /**
     * 在后面(内)插入空行
     */
    private boolean paddingBottom;

    /**
     * 是否带
     */
    private boolean selfClosing;

    /**
     * 属性和特性自动折行
     */
    private boolean wrapProperty;



    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Set<String> getAttributeSet() {
        return attributeSet;
    }

    public void setAttributeSet(Set<String> attributeSet) {
        this.attributeSet = attributeSet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getChildren() {
        return children;
    }

    public void setChildren(List<Tag> children) {
        this.children = children;
    }

    public boolean isMargin() {
        return margin;
    }

    public void setMargin(boolean margin) {
        this.margin = margin;
    }

    public boolean isMarginTop() {
        return marginTop;
    }

    public void setMarginTop(boolean marginTop) {
        this.marginTop = marginTop;
    }

    public boolean isMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(boolean marginBottom) {
        this.marginBottom = marginBottom;
    }

    public boolean isPadding() {
        return padding;
    }

    public void setPadding(boolean padding) {
        this.padding = padding;
    }

    public boolean isPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(boolean paddingTop) {
        this.paddingTop = paddingTop;
    }

    public boolean isPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(boolean paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public boolean isSelfClosing() {
        return selfClosing;
    }

    public void setSelfClosing(boolean selfClosing) {
        this.selfClosing = selfClosing;
    }

    public boolean isWrapProperty() {
        return wrapProperty;
    }

    public void setWrapProperty(boolean wrapProperty) {
        this.wrapProperty = wrapProperty;
    }

    /**
     * 添加一个属性
     */
    public Tag addChild(Tag tag) {
        if (children == null) {
            children = Lists.newArrayList();
        }
        children.add(tag);
        return this;
    }

    /**
     * 添加一个属性
     */
    public Tag addProperty(String propertyKey, String propertyValue) {
        if (propertyMap == null) {
            propertyMap = new LinkedHashMap<>();
        }
        propertyMap.put(propertyKey, propertyValue);
        return this;
    }

    /**
     * 添加一个特性
     */
    public Tag addAttribute(String attribute) {
        if (attributeSet == null) {
            attributeSet = new HashSet<String>();
        }
        attributeSet.add(attribute);
        return this;
    }
}
