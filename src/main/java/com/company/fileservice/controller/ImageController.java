package com.company.fileservice.controller;

import com.company.fileservice.dto.ImageDto;
import com.company.fileservice.dto.ResponseDto;
import com.company.fileservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/create")
    public ResponseDto<ImageDto> create(@RequestBody ImageDto dto) {
        return imageService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<ImageDto> get(@PathVariable("id") Integer id) {
        return imageService.get(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<ImageDto> update(@PathVariable("id") Integer id, @RequestBody ImageDto dto) {
        return imageService.update(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<ImageDto> delete(@PathVariable("id") Integer id) {
        return imageService.delete(id);
    }

    @GetMapping("/all")
    public ResponseDto<Page<ImageDto>> getAll(@RequestParam Map<String, String> params) {
        return imageService.getAll(params);
    }

    @GetMapping("/get-images-by-user/{id}")
    public ResponseDto<Set<ImageDto>> getImagesByUsersId(@PathVariable("id") Integer id) {
        return imageService.getImagesByUsersId(id);
    }
}