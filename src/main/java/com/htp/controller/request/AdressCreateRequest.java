package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AdressCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String street;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 7)
    private String houseNumber;

    @Min(1)
    private Integer flatNumber;

    @Min(1)
    private Integer floorNumber;

    @Min(1)
    private Integer porchNumber;

    private Boolean isPizza;


}
