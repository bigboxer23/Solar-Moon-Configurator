package com.bigboxer23.solar_moon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** */
@OpenAPIDefinition(
		info =
				@Info(
						title = "Solar Moon API",
						version = "1",
						description = "Provide ability to manage configuration of solar moon service",
						contact =
								@Contact(
										name = "bigboxer23@gmail.com",
										url = "https://github.com/bigboxer23/Solar-Moon-Configurator")))
@SpringBootApplication
public class SolarMoonConfiguratorApp {
	public static void main(String[] args) {
		SpringApplication.run(SolarMoonConfiguratorApp.class, args);
	}
}
