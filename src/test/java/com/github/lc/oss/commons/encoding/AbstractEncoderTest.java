package com.github.lc.oss.commons.encoding;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public abstract class AbstractEncoderTest extends AbstractTest {
    protected abstract Encoder getEncoder();

    @Test
    public void test_data() {
        try {
            this.getEncoder().encode((byte[]) null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        try {
            this.getEncoder().encode((String) null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        try {
            this.getEncoder().decode(null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        try {
            this.getEncoder().decodeString(null);
            Assertions.fail("Expected Excetion");
        } catch (NullPointerException ex) {
            // pass
        }

        Assertions.assertEquals("", this.getEncoder().encode(new byte[0]));
        Assertions.assertArrayEquals(new byte[0], this.getEncoder().decode(""));
    }

    protected void test_encodeDecode(final String text, final String encoded) {
        byte[] data = text.getBytes(StandardCharsets.UTF_8);

        String result = this.getEncoder().encode(data);
        Assertions.assertEquals(encoded, result);

        byte[] result2 = this.getEncoder().decode(result);
        Assertions.assertArrayEquals(data, result2);
        Assertions.assertEquals(text, new String(result2, StandardCharsets.UTF_8));

        String result3 = this.getEncoder().decodeString(result);
        Assertions.assertEquals(text, result3);

        String result4 = this.getEncoder().encode(text);
        Assertions.assertEquals(result, result4);
    }
}
