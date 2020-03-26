package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoughTypeCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String type;

    @Min(1)
    private Long priceId;
}
