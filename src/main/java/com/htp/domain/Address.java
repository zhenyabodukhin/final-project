package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_address")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "orders"})
@ToString(exclude = {"orders"})
public class Address {

    @Id
    @SequenceGenerator(name = "addressIdSeq", sequenceName = "m_address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressIdSeq")
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
    private Boolean isPizzaAddress;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "addressOrder")
    private Set<Order> orders = Collections.emptySet();
}
