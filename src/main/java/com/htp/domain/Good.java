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
public class Good {

    private Long id;

    private String goodName;

    private Double goodPrice;

    private Double goodWeight;

    private Long sizeId;

    private Long doughId;

    public Good(String goodName, Double goodPrice, Double goodWeight, Long sizeId, Long doughId){
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
}
