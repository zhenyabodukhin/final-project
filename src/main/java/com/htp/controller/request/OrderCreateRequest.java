package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderCreateRequest {
//TODO
    @Min(1)
    private Long userId;

    @Min(1)
    private Long addressId;

    private Timestamp time;

    @Pattern(regexp = "^((\\+3|7|5)+([0-9]){10})$")
    private String phoneNumber;

    private Boolean isDone;
}
