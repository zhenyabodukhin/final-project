package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_adress")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "orders"})
@ToString(exclude = {"orders"})
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "n_house")
    private String houseNumber;

    @Column(name = "n_flat")
    private Integer flatNumber;

    @Column(name = "n_floor")
    private Integer floorNumber;

    @Column(name = "n_porch")
    private Integer porchNumber;

    @Column(name = "is_pizza")
    private Boolean isPizzaAdress;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "adressOrder")
    private Set<Order> orders = Collections.emptySet();
}
