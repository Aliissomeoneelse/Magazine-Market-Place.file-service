package com.company.fileservice.service.mapper;

import com.company.fileservice.dto.ImageDto;
import com.company.fileservice.modul.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper(componentModel = "spring")
public abstract class ImageMapper {
    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public  abstract Image toEntity(ImageDto dto);

    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public  abstract  ImageDto toDto(Image image);
}
