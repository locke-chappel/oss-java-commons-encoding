package io.github.lc.oss.commons.encoding;

import java.nio.charset.StandardCharsets;

class Base64 implements Encoder {
    @Override
    public String encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    @Override
    public String encode(String data) {
        return this.encode(data.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] decode(String data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    @Override
    public String decodeString(String data) {
        return new String(this.decode(data), StandardCharsets.UTF_8);
    }
}
