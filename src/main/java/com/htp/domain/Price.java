package com.htp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    public Price (Double price) {
        this.price = price;
    }
}
