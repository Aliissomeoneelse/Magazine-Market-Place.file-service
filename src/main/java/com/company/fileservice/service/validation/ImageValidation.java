package com.company.fileservice.service.validation;

import com.company.fileservice.dto.ErrorDto;
import com.company.fileservice.dto.ImageDto;
import com.company.fileservice.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageValidation {
    private final ImageRepository imageRepository;

    public List<ErrorDto> validate(ImageDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        // Validate path
        if (dto.getPath() == null || dto.getPath().isEmpty()) {
            errors.add(new ErrorDto("path", "Image path is required."));
        }

        // Validate type
        if (dto.getType() == null || dto.getType().isEmpty()) {
            errors.add(new ErrorDto("type", "Image type is required."));
        }

        // Validate size
        if (dto.getSize() == null || dto.getSize() <= 0) {
            errors.add(new ErrorDto("size", "Invalid image size."));
        }

        // Validate token
        if (dto.getToken() == null || dto.getToken().isEmpty()) {
            errors.add(new ErrorDto("token", "Image token is required."));
        }

        // Validate uniqueness of path
        if (imageRepository.existsByToken(dto.getToken())) {
            errors.add(new ErrorDto("token", "Image with the same token already exists."));
        }

        return errors;
    }
}