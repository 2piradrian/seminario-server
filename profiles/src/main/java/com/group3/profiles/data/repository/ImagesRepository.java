package com.group3.profiles.data.repository;

import com.group3.profiles.data.feign.images_server.repository.ImagesServerRepositoryI;
import com.group3.profiles.domain.repository.ImagesRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ImagesRepository implements ImagesRepositoryI {

    private final ImagesServerRepositoryI repository;

    @Override
    public String uploadImage(String base64Image, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("base64Image", base64Image);
        payload.put("secret", secret);

        return this.repository.upload(payload).getImageId();
    }

}
