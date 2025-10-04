package com.group3.posts.domain.repository;

public interface ImagesRepositoryI {

    String upload(String base64Image, String secret);

    void delete(String imageId, String secret);

}
