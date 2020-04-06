package com.htp.security.util;

import com.htp.security.config.JwtTokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenUtils {

    private final JwtTokenConfig jwtTokenConfig;
}
