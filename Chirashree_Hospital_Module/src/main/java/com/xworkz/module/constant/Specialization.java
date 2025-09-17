package com.xworkz.module.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Specialization {
    CARDIOLOGIST("Cardiologist"),
    NEUROLOGIST("Neurologist"),
    ORTHOPEDIC("Orthopedic"),
    DERMATOLOGIST("Dermatologist"),
    PEDIATRICIAN("Pediatrician"),
    GENERAL_PHYSICIAN("General Physician");

    private final String displayName;

}
