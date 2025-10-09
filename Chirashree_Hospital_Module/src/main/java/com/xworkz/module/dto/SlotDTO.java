package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO {

    @NotNull(message = "Specialization name should not be empty")
    private String specializationName;


    @NotNull(message = "Start time required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @NotNull(message = "End time required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;





}
