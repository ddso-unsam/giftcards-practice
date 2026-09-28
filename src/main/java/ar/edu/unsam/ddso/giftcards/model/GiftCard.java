package ar.edu.unsam.ddso.giftcards.model;

import ar.edu.unsam.ddso.giftcards.model.enums.GiftCardStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {

    private Long id;

    private Customer customer;

    private Company company;

    private LocalDateTime creationDate;

    private List<GiftCardUsage> usages = new ArrayList<>();

    private GiftCardStatus status;

    private BigDecimal amount;
}
