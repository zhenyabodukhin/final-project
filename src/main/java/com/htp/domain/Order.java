package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_orders")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "userOrder", "addressOrder", "orderGoods"})
@ToString(exclude = {"userOrder", "addressOrder", "orderGoods"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_done")
    private boolean isDone;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userOrder;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address addressOrder;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private Set<OrderGood> orderGoods = Collections.emptySet();
}
