package ar.edu.unsam.ddso.giftcards.dto;

import ar.edu.unsam.ddso.giftcards.model.enums.GiftCardStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GiftCardResponseDTO(
        Long id,
        Long customerId,
        Long companyId,
        LocalDateTime creationDate,
        GiftCardStatus status,
        BigDecimal amount) {}
