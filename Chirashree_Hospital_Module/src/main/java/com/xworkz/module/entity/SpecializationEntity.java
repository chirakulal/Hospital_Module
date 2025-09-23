package com.xworkz.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialization")
@NamedQuery(name = "getAllSpecializations", query = "select e.specialization_name from SpecializationEntity e")
public class SpecializationEntity {

    @Id
    @Column(name = "id_specialization")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "specialization_name", nullable = false, length = 50)
    @NotNull(message = "Specialization name should not be empty")
    @Size(min = 3, max = 50, message = "Specialization name must be between 3 and 50 characters")
    private String specialization_name;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


}