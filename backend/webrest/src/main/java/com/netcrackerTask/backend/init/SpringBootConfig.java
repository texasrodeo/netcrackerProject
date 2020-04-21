package com.netcrackerTask.backend.init;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.netcrackerTask.backend.business", "com.netcrackerTask.backend"})
@EnableJpaRepositories("com.netcrackerTask.backend.business.persistence")
@EntityScan("com.netcrackerTask.backend.business.entity")
public class  SpringBootConfig {

}
