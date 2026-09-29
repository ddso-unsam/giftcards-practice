package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.GiftCard;

import java.util.Optional;

public interface GiftCardRepository {

    GiftCard save(GiftCard giftCard);

    Optional<GiftCard> findById(Long id);
}
