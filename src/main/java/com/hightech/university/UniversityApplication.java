package com.hightech.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UniversityApplication {

	public static void main(String[] args) { SpringApplication.run(UniversityApplication.class, args); }

}
