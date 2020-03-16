package com.htp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.htp")
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.htp")
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DatabaseConfig.class, JpaConfig.class})
public class AppConfig {
}
