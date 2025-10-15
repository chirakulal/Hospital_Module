package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patent_profile")
public class PatientProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimage")
    private int id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id_patient")
    private PatientEntity patientEntity;


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
