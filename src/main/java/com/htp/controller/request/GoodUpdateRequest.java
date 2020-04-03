package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GoodUpdateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String goodName;

    @Min(1)
    private Long priceId;

    @DecimalMin(value = "0.001")
    private Double weight;

    @Min(1)
    private Long sizeId;

    @Min(1)
    private Long doughId;

    private String ingredients;
}
