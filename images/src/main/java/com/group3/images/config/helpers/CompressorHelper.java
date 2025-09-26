package com.group3.images.config.helpers;

import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CompressorHelper {

    public static byte[] resizeAndCompress(byte[] imageBytes, int width, int height) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream in = new ByteArrayInputStream(imageBytes)) {
            Thumbnails.of(in)
                    .size(width, height)
                    .outputFormat("webp")
                    .outputQuality(1.0)
                    .toOutputStream(baos);
        }
        return baos.toByteArray();
    }

    public static byte[] compressUntilUnderSize(byte[] imageBytes, int maxSizeMb) throws Exception {
        final long MAX_SIZE = (long) maxSizeMb * 1024 * 1024;
        double quality = 0.9;
        byte[] compressed = imageBytes;

        while (compressed.length > MAX_SIZE && quality > 0.1) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (InputStream in = new ByteArrayInputStream(imageBytes)) {
                Thumbnails.of(in)
                        .scale(1.0)
                        .outputFormat("webp")
                        .outputQuality(quality)
                        .toOutputStream(baos);
            }
            compressed = baos.toByteArray();
            quality -= 0.1;
        }

        if (compressed.length > MAX_SIZE) {
            return null;
        }

        return compressed;
    }
}