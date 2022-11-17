package com.github.lc.oss.commons.encoding;

import org.junit.jupiter.api.Test;

public class Base64Test extends AbstractEncoderTest {
    @Override
    protected Encoder getEncoder() {
        return new Base64();
    }

    @Test
    public void test_encodeDecode() {
        this.test_encodeDecode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
                "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXpBQkNERUZHSElKS0xNTk9QUVJTVFVWV1hZWjAxMjM0NTY3ODk=");
    }
}
