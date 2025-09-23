package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {


    private String originalImageName;
    private String fileType;
    private long fileSize; // in bytes
    private String filePath;
    private Timestamp dateTime; // when the image was uploaded
    private String savedName;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;
}
