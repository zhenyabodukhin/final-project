package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserCreateRequest {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50)
    private String login;

    @NotEmpty
    @NotNull
    @Size(min = 6, max = 50)
    private String password;
}
