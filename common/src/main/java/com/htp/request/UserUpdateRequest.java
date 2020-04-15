package com.htp.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserUpdateRequest {

    @NotEmpty
    @NotNull
    @Size(min = 6, max = 100)
    private String login;

    @NotEmpty
    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
}
