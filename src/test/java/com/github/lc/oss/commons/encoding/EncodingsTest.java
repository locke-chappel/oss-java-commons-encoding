package com.github.lc.oss.commons.encoding;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractTest;

public class EncodingsTest extends AbstractTest {
    @Test
    public void test_caching() {
        Set<Encodings> expected = new HashSet<>(Arrays.asList(Encodings.values()));
        Set<Encoder> actual = Encodings.all();

        Assertions.assertNotSame(expected, actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(expected.containsAll(actual));
        Assertions.assertTrue(actual.containsAll(expected));

        Assertions.assertTrue(Encodings.hasName("base64"));
        Assertions.assertTrue(Encodings.hasName("BASE64"));

        Assertions.assertSame(Encodings.Base64, Encodings.byName("Base64"));
        Assertions.assertSame(Encodings.Base64, Encodings.byName("bAsE64"));

        Assertions.assertSame(Encodings.Hex, Encodings.tryParse("Hex"));
        Assertions.assertSame(Encodings.Hex, Encodings.tryParse("hEX"));
    }

    @Test
    public void test_functions() {
        Assertions.assertTrue(Encodings.Base64 instanceof Encoder);
        byte[] data = "Test".getBytes(StandardCharsets.UTF_8);
        Assertions.assertEquals("VGVzdA==", Encodings.Base64.encode(data));
        Assertions.assertEquals("VGVzdA==", Encodings.Base64.encode("Test"));
        Assertions.assertArrayEquals(data, Encodings.Hex.decode("54657374"));
        Assertions.assertEquals("Test", Encodings.Hex.decodeString("54657374"));
    }
}
