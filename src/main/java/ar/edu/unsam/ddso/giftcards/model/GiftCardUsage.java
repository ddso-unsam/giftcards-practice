package ar.edu.unsam.ddso.giftcards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCardUsage {

    private Long id;

    private LocalDateTime date;

    private GiftCard giftCard;

    private String product;

    private String place;
}
