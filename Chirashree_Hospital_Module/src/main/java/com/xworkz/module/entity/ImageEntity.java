package com.xworkz.module.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimage")
    private int id;

    @Column(name = "original_image_name", nullable = false)
    private String originalImageName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "date_time")
    private Timestamp dateTime;

    @Column(name = "saved_name", nullable = false)
    private String savedName;


}
