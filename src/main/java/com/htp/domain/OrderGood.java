package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class OrderGood {

    private Long id;

    private Long orderId;

    private Long goodId;

    private Integer goodCount;

    public OrderGood(Long orderId, Long goodId, Integer goodCount){
        this.orderId = orderId;
        this.goodId = goodId;
        this.goodCount = goodCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
