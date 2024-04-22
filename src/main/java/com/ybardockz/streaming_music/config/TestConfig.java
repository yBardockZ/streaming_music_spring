package com.ybardockz.streaming_music.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ybardockz.streaming_music.entities.Album;
import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.AlbumRepository;
import com.ybardockz.streaming_music.repositories.SongRepository;
import com.ybardockz.streaming_music.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		Album a1 = new Album("Unleashed", LocalDate.of(2016, 8, 5));
		Album a2 = new Album("Meteora", LocalDate.of(2003, 3, 25));
		Album a3 = new Album("Toxicity", LocalDate.of(2001, 9, 4));
		Album a4 = new Album("Nightmare!", LocalDate.of(2010, 7, 23));
		Album a5 = new Album("Avenged Sevenfold", LocalDate.of(2007, 10, 30));
		Album a6 = new Album("Rise", LocalDate.of(2013, 6, 25));
		
		Song s1 = new Song(null, "The Resistance", 3.52, a1);
		Song s2 = new Song(null, "Numb", 3.08, a2);
		Song s3 = new Song(null, "Chop Suey!", 3.30, a3);
		Song s4 = new Song(null, "Nightmare", 6.14, a4);
		Song s5 = new Song(null, "Gunsliger", 4.11, a5);
		Song s6 = new Song(null, "Not gonna die", 3.45, a6);
		
		a1.addSong(s1);
		a2.addSong(s2);
		a3.addSong(s3);
		a4.addSong(s4);
		a5.addSong(s5);
		a6.addSong(s6);
		
		albumRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6));
		songRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
		
		User u1 = new User(null, "Thalles", "thalles_leopoldino@outlook.com", "3214");
		User u2 = new User(null, "Leonardo", "leo@gmail.com", "leo1010");

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		
		
	}

}
