package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SizeCreateRequest {

    @Min(1)
    private Integer size;

    @Min(1)
    private Long priceId;
}
