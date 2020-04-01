package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomGoodCreateRequest {

    @Min(1)
    private Long sizeId;

    @Min(1)
    private Long doughId;

    @Min(0)
    private Integer peperoniCount;

    @Min(0)
    private Integer chickenCount;

    @Min(0)
    private Integer tomatoCount;
}
