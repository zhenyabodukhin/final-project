package com.htp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_price")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "goods", "sizes", "doughTypes"})
@ToString(exclude = {"goods", "sizes", "doughTypes"})
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "priceGood")
//    private Set<Good> goods = Collections.emptySet();
//
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "priceSize")
//    private Set<Size> sizes = Collections.emptySet();
//
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "priceDoughType")
//    private Set<DoughType> doughTypes = Collections.emptySet();
}
