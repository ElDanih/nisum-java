package com.nisum.jpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class JpaConfig {

    //h2
    @Bean
    public DBCredential dbSecret(Environment env) {
        return DBCredential.builder()
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
    }

    @Bean
    public DataSource datasource(DBCredential secret, @Value("${spring.datasource.driverClassName}") String driverClass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(secret.getUrl());
        config.setUsername(secret.getUsername());
        config.setPassword(secret.getPassword());
        config.setDriverClassName(driverClass);
        return new HikariDataSource(config);
    }

    //CONEXION DB POSTGRESQL
   /* @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("admin");
        config.setSchema("nisum");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10); // Replace with your max pool size
        config.setMinimumIdle(5); // Replace with your min pool size
        config.setIdleTimeout(SECONDS.toMillis(300000)); // Replace with your max time live in ms
        return new HikariDataSource(config);
    }*/
}
