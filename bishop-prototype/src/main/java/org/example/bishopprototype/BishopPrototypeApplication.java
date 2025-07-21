package org.example.bishopprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"org.example.bishopprototype",
		"org.example.synthetichumancorestarter"
})
public class BishopPrototypeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BishopPrototypeApplication.class, args);
	}
}
