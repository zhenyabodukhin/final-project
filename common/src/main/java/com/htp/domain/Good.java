package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_goods")
@Data
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "orderGoods", "priceGood", "sizeGood", "doughTypeGood"})
@ToString(exclude = {"orderGoods", "priceGood", "sizeGood", "doughTypeGood"})
public class Good {


    @Id
    @SequenceGenerator(name = "goodIdSeq", sequenceName = "m_goods_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goodIdSeq")
    private Long id;

    @Column(name = "name")
    private String goodName;

    @Column(name = "price_id")
    private Long priceId;

    @Column(name = "weight")
    private Double goodWeight;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "dough_id")
    private Long doughId;

    @Column(name = "ingredients")
    private String ingredients;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "good")
    private Set<OrderGood> orderGoods = Collections.emptySet();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", insertable = false, updatable = false)
    private Price priceGood;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", insertable = false, updatable = false)
    private Size sizeGood;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dough_id", insertable = false, updatable = false)
    private DoughType doughTypeGood;
}
