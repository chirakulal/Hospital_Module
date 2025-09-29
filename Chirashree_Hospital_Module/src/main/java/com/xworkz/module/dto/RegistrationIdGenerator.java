package com.xworkz.module.dto;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.time.format.DateTimeFormatter;

public class RegistrationIdGenerator {

    private RegistrationIdGenerator() {
        // Prevent utility class from being instantiated
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    private static final String ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC = "0123456789";
    private static final int RANDOM_DIGIT_LENGTH = 6;

    /**
     * Generates a registration ID.
     * New Format: FIRST_3_CHARS-YYMMDD-NNNNNN
     * e.g., SAT-250929-123456
     * * @param firstName The patient's first name.
     * @return The generated registration ID string.
     */
    public static String generateRegistrationId(String firstName) {
        // 1. Get the name part (First three characters, uppercase)
        String namePart = "PTN"; // Default if name is null or too short
        if (firstName != null && !firstName.trim().isEmpty()) {
            String trimmedName = firstName.trim().toUpperCase();
            // Take the first 3 characters, or fewer if the name is shorter
            namePart = trimmedName.substring(0, Math.min(trimmedName.length(), 3));
        }

        // 2. Get the date part (YYMMDD)
        String datePart = LocalDate.now().format(DATE_FORMATTER);

        // 3. Generate the random numeric part (6 digits)
        String randomPart = generateRandomNumeric(RANDOM_DIGIT_LENGTH);

        // 4. Combine parts
        return namePart + "-" + datePart + "-" + randomPart;
    }

    /**
     * Helper to generate a random numeric string of specified length.
     */
    public static String generateRandomNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            // Generates a random digit (0-9)
            int index = random.nextInt(NUMERIC.length());
            sb.append(NUMERIC.charAt(index));
        }
        return sb.toString();
    }

    /**
     * NOTE: Renamed the original method to clarify its new purpose (numeric only).
     * If you still need the original alphanumeric function, uncomment and use it.
     */
    // public static String generateRandomAlphanumeric(int length) {
    //     String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    //     StringBuilder sb = new StringBuilder(length);
    //     ThreadLocalRandom random = ThreadLocalRandom.current();
    //     for (int i = 0; i < length; i++) {
    //         int index = random.nextInt(ALPHANUMERIC.length());
    //         sb.append(ALPHANUMERIC.charAt(index));
    //     }
    //     return sb.toString();
    // }
}

