package com.htp;

import com.htp.config.core.DatabaseConfig;
import com.htp.config.core.JpaConfig;
import com.htp.config.swagger.SwaggerConfig;
import com.htp.security.config.JwtTokenConfig;
import com.htp.security.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.htp"},
        exclude = {
                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Import({
        DatabaseConfig.class,
        JpaConfig.class,
        SwaggerConfig.class,
        WebSecurityConfig.class,
        JwtTokenConfig.class
})
public class StartApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
