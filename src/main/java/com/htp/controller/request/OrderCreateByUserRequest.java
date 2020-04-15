package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderCreateByUserRequest {

    @Min(1)
    @Max(2147483647)
    private Long userId;

    @Min(1)
    @Max(2147483647)
    private Long addressId;

    @Pattern(regexp = "^((\\+3|7|5)+([0-9]){10})$")
    private String phoneNumber;
}
