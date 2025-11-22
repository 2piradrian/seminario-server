package com.group3.images.presentation.controller;

import com.group3.images.domain.dto.images.mapper.ImagesMapper;
import com.group3.images.domain.dto.images.request.DeleteImageReq;
import com.group3.images.domain.dto.images.request.LoadImageReq;
import com.group3.images.domain.dto.images.request.UploadImageReq;
import com.group3.images.presentation.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService service;

    @PostMapping
    public ResponseEntity<?> upload(
            @RequestBody Map<String, Object> payload
    ) {
        UploadImageReq dto = ImagesMapper.upload().toRequest(payload);

        return ResponseEntity.ok(this.service.uploadImage(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody Map<String, Object> payload
    ) {
        DeleteImageReq dto = ImagesMapper.delete().toRequest(payload);
        this.service.deleteImage(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String imageId
    ) {
        LoadImageReq dto = ImagesMapper.load().toRequest(imageId);
        Resource resource = this.service.loadImage(dto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/webp")
                .body(resource);
    }

}
