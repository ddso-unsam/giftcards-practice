package ar.edu.unsam.ddso.giftcards.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record GiftCardCreateRequestDTO(
        @NotNull(message = "customerId is required") Long customerId,
        @NotNull(message = "companyId is required") Long companyId,
        @NotNull(message = "amount is required") @Positive(message = "amount must be positive")
                BigDecimal amount) {}
