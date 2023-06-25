package com.snsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", System.getenv("CLOUDINARY_NAME"),
				"api_key", System.getenv("CLOUDINARY_API_KEY"), "api_secret", System.getenv("CLOUDINARY_API_SECRET")));
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();
		SpringApplication.run(Application.class, args);
	}
}
