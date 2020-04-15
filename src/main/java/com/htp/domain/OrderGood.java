package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_goods")
@Data
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "order", "good"})
@ToString(exclude = {"order", "good"})
public class OrderGood {

    @Id
    @SequenceGenerator(name = "orderGoodIdSeq", sequenceName = "order_goods_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderGoodIdSeq")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "count")
    private Integer goodCount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id", insertable = false, updatable = false)
    private Good good;
}
