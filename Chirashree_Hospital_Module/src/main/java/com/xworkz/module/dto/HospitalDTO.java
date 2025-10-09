package com.xworkz.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {

    @NotNull(message = "Email should not be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Email should send with @gmail.com")
    private String email;

    @NotNull(message = "Should not be empty")
    private String otp;

    private String password;
    private LocalDateTime time;

}
