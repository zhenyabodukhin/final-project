package com.htp.request;

import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PriceCreateRequest {

    @DecimalMin(value = "0.01")
    @DecimalMax(value = "2147483647")
    private Double price;
}
