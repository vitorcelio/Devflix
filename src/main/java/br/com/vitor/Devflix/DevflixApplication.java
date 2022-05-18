package br.com.vitor.Devflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class DevflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevflixApplication.class, args);
	}

}
