package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_size")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "goods", "priceSize"})
@ToString(exclude = {"goods", "priceSize"})
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    private Integer sizeCount;

    @Column(name = "price_id")
    private Long priceId;

//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "sizeGood")
//    private Set<Good> goods = Collections.emptySet();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", insertable = false, updatable = false)
    private Price priceSize;
}
