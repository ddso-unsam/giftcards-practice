package ar.edu.unsam.ddso.giftcards.dto;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public record CompanyCreateRequestDTO(
        String name,
        String description,
        @NotBlank(message = "cuil is required") String cuil,
        @URL(regexp = "^https?://.*", message = "notificationUrl must be a valid http(s) URL")
                String notificationUrl) {}
