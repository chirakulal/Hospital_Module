package com.xworkz.module.dto;

import java.sql.Timestamp;

public class PatientProfileDTO {

    private String originalImageName;
    private String fileType;
    private long fileSize; // in bytes
    private String filePath;
    private Timestamp dateTime; // when the image was uploaded
    private String savedName;

}
