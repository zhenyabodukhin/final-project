package com.htp.config.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Value("${driver}")
    private String driverName;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    @Value("${initial-size}")
    private String initialSize;

    @Value("${max-active}")
    private String maxActive;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(initialSize)));
        dataSource.setUsername(login);
        dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(maxActive)));
        return dataSource;
    }
}
