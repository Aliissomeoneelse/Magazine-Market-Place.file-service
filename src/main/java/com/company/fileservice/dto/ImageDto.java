package com.company.fileservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImageDto {
    private  Integer imageId;
    private  String  path;
    private  String type;
    private  Integer size;
    private  String token;
    private LocalDateTime createAt;
    private  LocalDateTime updateAt;
    private  LocalDateTime deleteAt;
}
