package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient_symptomImages")
public class PatientSymptomsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id_patient")
    private PatientEntity patientEntity;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_original_Name")
    private String  imageOriginalName;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_size")
    private long size;




}
