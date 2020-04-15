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
public class SizeCreateRequest {

    @Min(1)
    @Max(2147483647)
    private Integer size;

    @Min(1)
    @Max(2147483647)
    private Long priceId;
}
