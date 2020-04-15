package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoughTypeCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String type;

    @Min(1)
    @Max(2147483647)
    private Long priceId;
}
