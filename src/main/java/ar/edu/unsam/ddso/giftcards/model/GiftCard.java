package ar.edu.unsam.ddso.giftcards.model;

import ar.edu.unsam.ddso.giftcards.model.enums.GiftCardStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gift_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Company company;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "giftCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GiftCardUsage> usages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GiftCardStatus status;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
}
