package com.netcrackerTask.backend.init;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.netcrackerTask.backend.business", "com.netcrackerTask.backend"})
public class  SpringBootConfig {

}
