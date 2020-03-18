package com.htp.config.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;


import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("datasource")
public class DatabaseConfig {

    private String driverName;

    private String url;

    private String login;

    private String password;

    private String initialSize;

    private String maxActive;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/project_pizza");
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull("10")));
        dataSource.setUsername("postgres");
        dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull("7")));
        return dataSource;
    }
}
