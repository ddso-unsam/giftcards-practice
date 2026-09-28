package ar.edu.unsam.ddso.giftcards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;

    private String name;

    private String description;

    private String cuil;

    private String notificationUrl;
}
