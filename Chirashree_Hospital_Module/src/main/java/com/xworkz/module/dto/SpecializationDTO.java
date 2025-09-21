package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationDTO {

    private long id;

    @NotNull(message = "Specialization name should not be empty")
    @Size(min = 3, max = 50, message = "Specialization name must be between 3 and 50 characters")
    private String name;

    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;

}