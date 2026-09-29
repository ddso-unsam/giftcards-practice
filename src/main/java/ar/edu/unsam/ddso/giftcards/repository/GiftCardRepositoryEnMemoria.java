package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Company;
import ar.edu.unsam.ddso.giftcards.model.Customer;
import ar.edu.unsam.ddso.giftcards.model.GiftCard;
import ar.edu.unsam.ddso.giftcards.model.enums.GiftCardStatus;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class GiftCardRepositoryEnMemoria implements GiftCardRepository {

    private final Map<Long, GiftCard> giftCards = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public GiftCardRepositoryEnMemoria() {
        Company acme =
                new Company(1L, "Acme", "Retail de electrodomésticos", "30-11111111-1", null);
        Customer juana = new Customer(1L, "Juana Pérez", "Clienta frecuente", "27-33333333-3");
        save(
                new GiftCard(
                        null,
                        juana,
                        acme,
                        LocalDateTime.now(),
                        new ArrayList<>(),
                        GiftCardStatus.ACTIVE,
                        new BigDecimal("5000.00")));
    }

    @Override
    public GiftCard save(GiftCard giftCard) {
        if (giftCard.getId() == null) {
            giftCard.setId(nextId.getAndIncrement());
        }
        giftCards.put(giftCard.getId(), giftCard);
        return giftCard;
    }

    @Override
    public Optional<GiftCard> findById(Long id) {
        return Optional.ofNullable(giftCards.get(id));
    }
}
