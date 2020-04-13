package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderUpdateAdminRequest {

    @Min(1)
    private Long userId;

    @Min(1)
    private Long addressId;

    @Pattern(regexp = "^((\\+3|7|5)+([0-9]){10})$")
    private String phoneNumber;

    private Boolean isDone;
}
