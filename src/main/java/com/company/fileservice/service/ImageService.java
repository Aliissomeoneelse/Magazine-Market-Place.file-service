package com.company.fileservice.service;

import com.company.fileservice.dto.ErrorDto;
import com.company.fileservice.dto.ImageDto;
import com.company.fileservice.dto.ResponseDto;
import com.company.fileservice.modul.Image;
import com.company.fileservice.repository.ImageRepository;
import com.company.fileservice.repository.ImageRepositoryImpl;
import com.company.fileservice.service.mapper.ImageMapper;
import com.company.fileservice.service.validation.ImageValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ImageRepositoryImpl imageRepositoryImpl;
    private final ImageValidation imageValidation;


    public ResponseDto<ImageDto> create(ImageDto dto) {
        List<ErrorDto> errors = imageValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }
        try {
            Image image = imageMapper.toEntity(dto);
            image.setCreateAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("Image successfully created!")
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image while saving error: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ImageDto> get(Integer id) {
        Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<ImageDto>builder()
                .success(true)
                .message("OK")
                .data(imageMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<ImageDto> update(ImageDto dto, Integer id) {
        List<ErrorDto> errors = imageValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }
        Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            Image image = imageMapper.toEntity(dto);
            image.setImageId(optional.get().getImageId());
            image.setUpdateAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("Image successfully updated!")
                    .data(imageMapper.toDto(image))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image update error: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ImageDto> delete(Integer id) {
        Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            Image image = optional.get();
            imageRepository.delete(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("Image successfully deleted!")
                    .data(imageMapper.toDto(image))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image deletion error: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<ImageDto>> getAll(Map<String, String> params) {
        Page<ImageDto> imageDto = imageRepositoryImpl
                .getImages(params)
                .map(imageMapper::toDto);
        if (imageDto.isEmpty()) {
            return ResponseDto.<Page<ImageDto>>builder()
                    .message("No images found for the provided parameters: " + params)
                    .build();
        }
        return ResponseDto.<Page<ImageDto>>builder()
                .message("OK")
                .success(true)
                .data(imageDto)
                .build();
    }
}