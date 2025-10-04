package com.group3.config;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

public class PrefixedUUID {

    private static final SecureRandom random = new SecureRandom();

    public enum EntityType {
        USER((byte) 0b0001),
        PAGE((byte) 0b0010);

        private final byte prefix;

        EntityType(byte prefix) {
            this.prefix = prefix;
        }

        public byte getPrefix() {
            return prefix;
        }
    }

    public static UUID generate(EntityType type) {
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        randomBytes[0] &= 0x0F;
        randomBytes[0] |= (type.getPrefix() << 4);

        ByteBuffer bb = ByteBuffer.wrap(randomBytes);
        long mostSigBits = bb.getLong();
        long leastSigBits = bb.getLong();

        return new UUID(mostSigBits, leastSigBits);
    }

    public static EntityType resolveType(UUID id) {
        byte firstByte = (byte) (id.getMostSignificantBits() >>> 56);
        byte prefix = (byte) ((firstByte & 0xF0) >> 4);

        for (EntityType type : EntityType.values()) {
            if (type.getPrefix() == prefix) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown entity type prefix: " + prefix);
    }

}
