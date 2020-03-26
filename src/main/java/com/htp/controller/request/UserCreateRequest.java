package com.htp.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserCreateRequest {
    //TODO
    private String login;

    private String password;

    private Boolean isDeleted;
}
