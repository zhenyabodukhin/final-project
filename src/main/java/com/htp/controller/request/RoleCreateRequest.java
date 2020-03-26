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
public class RoleCreateRequest {

    @Min(1)
    private Long userId;

    @NotEmpty
    @NotNull
    @Size(min = 7, max = 20)
    private String role;
}
