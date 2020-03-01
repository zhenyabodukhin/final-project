package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Order {

    private Long id;

    private Long userId;

    private Long adressId;

    private Timestamp time;

    private boolean isDone;

    public Order(Long userId, Long adressId){
        this.userId = userId;
        this.adressId = adressId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
