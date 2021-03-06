package com.htp.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GoodCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String goodName;

    @Min(1)
    @Max(2147483647)
    private Long priceId;

    @DecimalMin(value = "0.001")
    @DecimalMax(value = "2147483647")
    private Double weight;

    @Min(1)
    @Max(2147483647)
    private Long sizeId;

    @Min(1)
    @Max(2147483647)
    private Long doughId;
}
