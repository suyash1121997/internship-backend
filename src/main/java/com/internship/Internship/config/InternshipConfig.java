package com.internship.Internship.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
@Slf4j
public class InternshipConfig {
//    @PostConstruct
    public void data() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://postgres:5432/internship", "postgres", "postgres")
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        log.info("Flyway Script Executed Successfully");

    }
}
