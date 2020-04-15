package com.htp.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleCreateRequest {

    @Min(1)
    @Max(2147483647)
    private Long userId;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 100)
    private String role;
}
