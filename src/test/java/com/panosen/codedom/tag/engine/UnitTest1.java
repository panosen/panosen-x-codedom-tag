package com.panosen.codedom.tag.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.tag.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class UnitTest1 {

    @Test
    public void Test() {

        Tag tag = PrepareCode();

        StringWriter stringWriter = new StringWriter();

        new TagEngine().generate(tag, new CodeWriter(stringWriter), new GenerateOptions());

        String actual = stringWriter.toString();

        String expeced = PrepareExpected();

        Assert.assertEquals(expeced, actual);
    }

    private String PrepareExpected() {
        return "<apple></apple>";
    }

    protected Tag PrepareCode() {
        Tag tag = new Tag();
        tag.setName("apple");
        return tag;
    }
}