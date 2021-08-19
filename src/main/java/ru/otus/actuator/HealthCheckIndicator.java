package ru.otus.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HealthCheckIndicator {

    @Value("SELECT 1 FROM DUAL")
    String validationquery;

    @Bean("smartDBHealthIndicator")
    public HealthIndicator smartDBHealthIndicator(@Autowired DataSource ds) {
        return new DataSourceHealthIndicator(ds, validationquery);
    }
}
