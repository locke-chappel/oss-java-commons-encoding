package io.github.lc.oss.commons.encoding;

public interface Encoder {
    String encode(byte[] data);

    String encode(String data);

    byte[] decode(String data);

    String decodeString(String data);
}
