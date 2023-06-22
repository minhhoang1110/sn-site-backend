package com.snsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		Cloudinary cloudinary = new Cloudinary(
				ObjectUtils.asMap("cloud_name", dotenv.get("CLOUDINARY_NAME", ""), "api_key",
						dotenv.get("CLOUDINARY_API_KEY", ""), "api_secret", dotenv.get("CLOUDINARY_API_SECRET", "")));
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();
		SpringApplication.run(Application.class, args);

	}
}
