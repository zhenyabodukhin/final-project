package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_sizes")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "priceSize"})
@ToString(exclude = {"priceSize"})
public class Size {

    @Id
    @SequenceGenerator(name = "sizeIdSeq", sequenceName = "m_size_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sizeIdSeq")
    private Long id;

    @Column(name = "size")
    private Integer sizeCount;

    @Column(name = "price_id")
    private Long priceId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", insertable = false, updatable = false)
    private Price priceSize;
}
