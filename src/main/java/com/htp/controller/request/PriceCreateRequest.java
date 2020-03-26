package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PriceCreateRequest {

    @DecimalMin(value = "0.01")
    private Double price;
}
