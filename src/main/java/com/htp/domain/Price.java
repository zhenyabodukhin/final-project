package com.htp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_prices")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id"})
@ToString
public class Price {

    @Id
    @SequenceGenerator(name = "priceIdSeq", sequenceName = "m_price_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priceIdSeq")
    private Long id;

    @Column(name = "price")
    private Double price;
}
