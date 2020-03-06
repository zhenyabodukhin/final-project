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
public class Size {

    private Long id;

    private Integer sizeCount;

    private Double sizePrice;

    public Size(Integer sizeCount, Double sizePrice){
        this.sizeCount = sizeCount;
        this.sizePrice = sizePrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
