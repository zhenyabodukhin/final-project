package com.htp.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomGoodCreateRequest {

    @Min(1)
    @Max(2147483647)
    private Long sizeId;

    @Min(1)
    @Max(2147483647)
    private Long doughId;

    @Min(0)
    @Max(2147483647)
    private Integer peperoni;

    @Min(0)
    @Max(2147483647)
    private Integer sausages;

    @Min(0)
    @Max(2147483647)
    private Integer bacon;

    @Min(0)
    @Max(2147483647)
    private Integer beef;

    @Min(0)
    @Max(2147483647)
    private Integer shrimp;

    @Min(0)
    @Max(2147483647)
    private Integer salami;

    @Min(0)
    @Max(2147483647)
    private Integer chicken;

    @Min(0)
    @Max(2147483647)
    private Integer tomato;

    @Min(0)
    @Max(2147483647)
    private Integer cheeseFeta;

    @Min(0)
    @Max(2147483647)
    private Integer dorBlue;

    @Min(0)
    @Max(2147483647)
    private Integer parmesan;

    @Min(0)
    @Max(2147483647)
    private Integer mozzarella;

    @Min(0)
    @Max(2147483647)
    private Integer mushrooms;

    @Min(0)
    @Max(2147483647)
    private Integer pepper;

    @Min(0)
    @Max(2147483647)
    private Integer pineapple;

    @Min(0)
    @Max(2147483647)
    private Integer oils;

    @Min(0)
    @Max(2147483647)
    private Integer cucumbers;
}
