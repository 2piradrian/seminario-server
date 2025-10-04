package com.group3.images.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.images.config.helpers.CompressorHelper;
import com.group3.images.config.helpers.SecretKeyHelper;
import com.group3.images.data.repository.ImageRepository;
import com.group3.images.domain.dto.images.mapper.ImagesMapper;
import com.group3.images.domain.dto.images.request.DeleteImageReq;
import com.group3.images.domain.dto.images.request.LoadImageReq;
import com.group3.images.domain.dto.images.request.UploadImageReq;
import com.group3.images.domain.dto.images.response.UploadImageRes;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
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

    @Override
    @SneakyThrows
    public UploadImageRes uploadImage(UploadImageReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        byte[] imageBytes = Base64.getDecoder().decode(dto.getBase64Image());

        String imageId = PrefixedUUID.generate(PrefixedUUID.EntityType.IMAGE).toString();
        String fileName = imageId + ".webp";

        byte[] processedImage = CompressorHelper.compressUntilUnderSize(imageBytes, 1);

        Path savedPath = imageRepository.save(processedImage, fileName);

        return ImagesMapper.upload().toResponse(savedPath.getFileName().toString());
    }

    @Override
    public void deleteImage(DeleteImageReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        imageRepository.delete(dto.getImageId());
    }

    @Override
    public Resource loadImage(LoadImageReq dto) {
        return imageRepository.load(dto.getImageId());
    }

}
