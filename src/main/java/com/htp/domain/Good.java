package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Table(name = "m_goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Good {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String goodName;

    @Column(name = "price_id")
    private Long goodPrice;

    @Column(name = "weight")
    private Double goodWeight;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "dough_id")
    private Long doughId;

    public Good(String goodName, Long goodPrice, Double goodWeight, Long sizeId, Long doughId) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodWeight = goodWeight;
        this.sizeId = sizeId;
        this.doughId = doughId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "dough_id", nullable = false)
    private DoughType doughType;
}
