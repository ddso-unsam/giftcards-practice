package ar.edu.unsam.ddso.giftcards.dto;

public record CompanyCreateRequestDTO(
        String name, String description, String cuil, String notificationUrl) {}
