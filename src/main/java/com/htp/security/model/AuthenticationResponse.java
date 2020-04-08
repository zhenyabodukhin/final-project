package com.htp.security.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private Long userId;

    private String login;

    private String authToken;
}
