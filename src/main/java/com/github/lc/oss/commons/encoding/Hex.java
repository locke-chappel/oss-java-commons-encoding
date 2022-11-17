package com.github.lc.oss.commons.encoding;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

class Hex implements Encoder {
    private static final Pattern IS_HEX = Pattern.compile("^(?:[0-9a-fA-F][0-9a-fA-F])+$");

    // http://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    @Override
    public String encode(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int j = 0; j < data.length; j++) {
            int v = data[j] & 0xFF;
            chars[j * 2] = Hex.HEX_CHARS[v >>> 4];
            chars[j * 2 + 1] = Hex.HEX_CHARS[v & 0x0F];
        }
        return new String(chars);
    }

    @Override
    public String encode(String data) {
        return this.encode(data.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] decode(String data) {
        if (data == null) {
            throw new NullPointerException();
        }

        String hex = data.trim();
        if (hex == "") {
            return new byte[0];
        }

        if (!Hex.IS_HEX.matcher(hex).matches()) {
            throw new IllegalArgumentException("Data string is not valid hexadecimal");
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

    @Override
    public String decodeString(String data) {
        return new String(this.decode(data), StandardCharsets.UTF_8);
    }
}
