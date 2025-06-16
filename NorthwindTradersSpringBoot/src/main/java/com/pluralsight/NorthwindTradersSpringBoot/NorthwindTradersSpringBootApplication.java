package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);
		ProductRunnerMenu menu = context.getBean(ProductRunnerMenu.class);
		menu.startMenu();
	}
}
