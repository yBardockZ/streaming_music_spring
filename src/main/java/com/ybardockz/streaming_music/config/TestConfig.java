package com.ybardockz.streaming_music.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	private UserRepository userRepository;
	
	
	public TestConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Thalles", "thalles_leopoldino@outlook.com", "3214");
		User u2 = new User(null, "Leonardo", "leo@gmail.com", "leo1010");

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		
		
	}

}
