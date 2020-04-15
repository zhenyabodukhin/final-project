package com.htp.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@ApiModel(description = "Object with values for authentication")
public class AuthenticationRequest {

    @NotEmpty
    @ApiModelProperty(required = true, dataType = "string", notes = "user login")
    private String userName;

    @NotEmpty
    @ApiModelProperty(required = true, dataType = "string", notes = "user password")
    private String password;
}
