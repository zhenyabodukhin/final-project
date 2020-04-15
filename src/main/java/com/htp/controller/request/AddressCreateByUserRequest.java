package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AddressCreateByUserRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String street;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 7)
    private String houseNumber;

    @Min(1)
    @Max(2147483647)
    private Integer flatNumber;

    @Min(1)
    @Max(2147483647)
    private Integer floorNumber;

    @Min(1)
    @Max(2147483647)
    private Integer porchNumber;
}
