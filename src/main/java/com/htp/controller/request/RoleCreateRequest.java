package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleCreateRequest {

    @Min(1)
    private Long userId;

    private String role;
}
