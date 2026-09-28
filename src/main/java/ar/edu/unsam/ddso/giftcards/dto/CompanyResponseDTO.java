package ar.edu.unsam.ddso.giftcards.dto;

public record CompanyResponseDTO(
        Long id, String name, String description, String cuil, String notificationUrl) {}
