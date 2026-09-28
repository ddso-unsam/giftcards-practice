package ar.edu.unsam.ddso.giftcards.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "consumos_giftcard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoGiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_card_id", nullable = false)
    private GiftCard giftCard;

    @Column(name = "fecha_uso", nullable = false)
    private LocalDate fechaUso;

    @Column(name = "lugar_uso", nullable = false)
    private String lugarUso;

    @Column(name = "producto_elegido", nullable = false)
    private String productoElegido;
}
