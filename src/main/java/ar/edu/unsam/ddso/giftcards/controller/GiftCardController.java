package ar.edu.unsam.ddso.giftcards.controller;

import ar.edu.unsam.ddso.giftcards.dto.GiftCardCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.GiftCardResponseDTO;
import ar.edu.unsam.ddso.giftcards.service.GiftCardService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gift-cards")
public class GiftCardController {

    private final GiftCardService giftCardService;

    public GiftCardController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @PostMapping
    public ResponseEntity<GiftCardResponseDTO> create(
            @Valid @RequestBody GiftCardCreateRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(giftCardService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCardResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(giftCardService.findById(id));
    }
}
