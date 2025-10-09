package com.xworkz.module.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {


    private String originalImageName;
    private String fileType;
    private long fileSize; // in bytes
    private String filePath;
    private Timestamp dateTime; // when the image was uploaded
    private String savedName;

}
