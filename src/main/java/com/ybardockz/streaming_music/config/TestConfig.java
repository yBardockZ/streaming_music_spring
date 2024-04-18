package com.ybardockz.streaming_music.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.SongRepository;
import com.ybardockz.streaming_music.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SongRepository songRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		Song s1 = new Song(null, "The Resistance", 3.52);
		Song s2 = new Song(null, "Numb", 3.08);
		Song s3 = new Song(null, "Chop Suey!", 3.30);
		Song s4 = new Song(null, "Nightmare", 6.14);
		Song s5 = new Song(null, "Gunsliger", 4.12);
		Song s6 = new Song(null, "Not gonna die", 3.45);
		
		songRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
		
		User u1 = new User(null, "Thalles", "thalles_leopoldino@outlook.com", "3214");
		User u2 = new User(null, "Leonardo", "leo@gmail.com", "leo1010");

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		
		
	}

}
