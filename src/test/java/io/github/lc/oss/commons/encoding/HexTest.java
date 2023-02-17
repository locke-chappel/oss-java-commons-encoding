package io.github.lc.oss.commons.encoding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HexTest extends AbstractEncoderTest {
    @Override
    protected Encoder getEncoder() {
        return new Hex();
    }

    @Test
    public void test_encodeDecode() {
        this.test_encodeDecode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!",
                "6162636465666768696A6B6C6D6E6F707172737475767778797A4142434445464748494A4B4C4D4E4F505152535455565758595A3031323334353637383921");
    }

    @Test
    public void test_decodeRange() {
        try {
            this.getEncoder().decode("A");
            Assertions.fail("Expected Exception");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Data string is not valid hexadecimal", ex.getMessage());
        }
    }
}
