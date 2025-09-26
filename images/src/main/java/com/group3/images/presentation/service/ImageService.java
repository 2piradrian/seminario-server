package com.group3.images.presentation.service;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.images.config.helpers.CompressorHelper;
import com.group3.images.config.helpers.SecretKeyHelper;
import com.group3.images.data.repository.ImageRepository;
import com.group3.images.domain.dto.images.mapper.ImagesMapper;
import com.group3.images.domain.dto.images.request.DeleteImageReq;
import com.group3.images.domain.dto.images.request.UploadImageReq;
import com.group3.images.domain.dto.images.response.UploadImageRes;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ImageService implements ImageServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final ImageRepository imageRepository;

    @SneakyThrows
    public UploadImageRes uploadImage(UploadImageReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        byte[] imageBytes = Base64.getDecoder().decode(dto.getBase64Image());

        String fileName = UUID.randomUUID() + ".webp";

        byte[] processedImage = CompressorHelper.compressUntilUnderSize(imageBytes, 2);

        Path savedPath = imageRepository.save(processedImage, fileName);

        return ImagesMapper.create().toResponse(savedPath.getFileName().toString());
    }

    public void deleteImage(DeleteImageReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        imageRepository.delete(dto.getImageId());
    }

    public Resource loadImage(String imageName) {
        try {
            return imageRepository.load(imageName);
        } catch (Exception e) {
            throw new ErrorHandler(ErrorType.NOT_FOUND, "Imagen no encontrada: " + imageName, e);
        }
    }

}
