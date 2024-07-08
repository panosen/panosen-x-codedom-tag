package com.panosen.codedom.tag.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.tag.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class UnitTest3 {

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
        return "<apple" + System.lineSeparator() +
                "    key0=\"value0\"" + System.lineSeparator() +
                "    key1=\"value1\"" + System.lineSeparator() +
                "    key2=\"value2\"></apple>";
    }

    protected Tag PrepareCode() {
        Tag tag = new Tag();
        tag.setWrapProperty(true);

        for (int i = 0; i < 3; i++) {
            tag.addProperty("key" + i, "value" + i);
        }

        tag.setName("apple");
        return tag;
    }
}