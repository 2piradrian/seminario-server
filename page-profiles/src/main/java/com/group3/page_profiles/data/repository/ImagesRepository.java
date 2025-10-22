package com.group3.page_profiles.data.repository;

import com.group3.page_profiles.data.datasource.images_server.repository.ImagesServerRepositoryI;
import com.group3.page_profiles.domain.repository.ImagesRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ImagesRepository implements ImagesRepositoryI {

    private final ImagesServerRepositoryI repository;


    // ======== Upload Image ========

    @Override
    public String upload(String base64Image, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("base64Image", base64Image);
        payload.put("secret", secret);

        return this.repository.upload(payload).getImageId();
    }


    // ======== Delete Image ========

    @Override
    public void delete(String imageId, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("imageId", imageId);
        payload.put("secret", secret);

        this.repository.delete(payload);
    }

}
