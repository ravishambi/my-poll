package com.ravish.mypoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.ravish.mypoll.controller")
public class MyPollApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPollApplication.class, args);
	}

}
