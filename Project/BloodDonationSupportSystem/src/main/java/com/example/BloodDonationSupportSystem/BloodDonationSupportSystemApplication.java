package com.example.BloodDonationSupportSystem;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloodDonationSupportSystemApplication {
	public static void main(String[] args) {
		// Load environment variables
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Set to System properties so Spring can use @Value
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(BloodDonationSupportSystemApplication.class, args);
	}
}
