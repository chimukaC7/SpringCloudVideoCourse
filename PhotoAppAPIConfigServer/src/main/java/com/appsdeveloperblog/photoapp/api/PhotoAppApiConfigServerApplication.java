package com.appsdeveloperblog.photoapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer//configure our application file and to enable our application to work as a config server.
public class PhotoAppApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiConfigServerApplication.class, args);
	}

}
