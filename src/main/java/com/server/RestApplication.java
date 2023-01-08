package com.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.server.repo.ServerRepo;

@EnableMongoRepositories
@SpringBootApplication
public class RestApplication implements CommandLineRunner{
	
	

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Bean
	public CommandLineRunner loadData(ServerRepo repo) {
		return (args) -> {};
	}

	

}
