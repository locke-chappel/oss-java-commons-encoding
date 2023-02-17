package io.github.lc.oss.commons.encoding;

import java.util.Set;

import io.github.lc.oss.commons.util.TypedEnumCache;

public enum Encodings implements Encoder {
    Base64(new Base64()),
    Hex(new Hex());

    private static final TypedEnumCache<Encodings, Encoder> CACHE = new TypedEnumCache<>(Encodings.class, false);

    public static final Set<Encoder> all() {
        return Encodings.CACHE.values();
    }

    public static Encoder byName(String name) {
        return Encodings.CACHE.byName(name);
    }

    public static boolean hasName(String name) {
        return Encodings.CACHE.hasName(name);
    }

    public static Encoder tryParse(String name) {
        return Encodings.CACHE.tryParse(name);
    }

    private final Encoder encoder;

    private Encodings(Encoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(byte[] data) {
        return this.encoder.encode(data);
    }

    @Override
    public String encode(String data) {
        return this.encoder.encode(data);
    }

    @Override
    public byte[] decode(String data) {
        return this.encoder.decode(data);
    }

    @Override
    public String decodeString(String data) {
        return this.encoder.decodeString(data);
    }
}
