package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.xml.ws.soap.Addressing;
import java.sql.Time;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTO {

    @NotNull(message = "Start time is required")

    private String startTime;

    @NotNull(message = "End time is required")

    private String  endTime;

}
