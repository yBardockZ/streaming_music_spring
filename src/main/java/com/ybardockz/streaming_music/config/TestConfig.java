package com.ybardockz.streaming_music.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ybardockz.streaming_music.entities.Album;
import com.ybardockz.streaming_music.entities.Artist;
import com.ybardockz.streaming_music.entities.Playlist;
import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.entities.User;
import com.ybardockz.streaming_music.repositories.AlbumRepository;
import com.ybardockz.streaming_music.repositories.ArtistRepository;
import com.ybardockz.streaming_music.repositories.PlaylistRepository;
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
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Thalles", "thalles_leopoldino@outlook.com", "3214");
		User u2 = new User(null, "Leonardo", "leo@gmail.com", "leo1010");
		
		Artist ar1 = new Artist("Skillet", "Skillet is an American Christian rock band formed in Memphis, Tennessee, "
				+ "in 1996. The band currently consists of husband and wife duo John Cooper");
		Artist ar2 = new Artist("Linkin Park", "Linkin Park is an American rock band from Agoura Hills, California.");
		Artist ar3 = new Artist("System of a Down", "System of a Down is an Armenian-American heavy metal band formed in Glendale, California, in 1994.");
		Artist ar4 = new Artist("Avenged Sevenfold", "Avenged Sevenfold (abbreviated as A7X) is an American heavy metal band from Huntington Beach, California, formed in 1999.");
		
		Album a1 = new Album("Unleashed", LocalDate.of(2016, 8, 5), Arrays.asList(ar1));
		Album a2 = new Album("Meteora", LocalDate.of(2003, 3, 25), Arrays.asList(ar2));
		Album a3 = new Album("Toxicity", LocalDate.of(2001, 9, 4), Arrays.asList(ar3));
		Album a4 = new Album("Nightmare!", LocalDate.of(2010, 7, 23), Arrays.asList(ar4));
		Album a5 = new Album("Avenged Sevenfold", LocalDate.of(2007, 10, 30), Arrays.asList(ar4));
		Album a6 = new Album("Rise", LocalDate.of(2013, 6, 25), Arrays.asList(ar1));
		
		
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
		
		Playlist p1 = u1.createPlaylist("pauladas para bater o pr", Arrays.asList(s4, s2, s3, s5));
		Playlist p2 = u2.createPlaylist("leo junior's playlist", Arrays.asList(s1, s6));
		
		songRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
		userRepository.saveAll(Arrays.asList(u1, u2));
		albumRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6));
		artistRepository.saveAll(Arrays.asList(ar1, ar2, ar3, ar4));
		playlistRepository.saveAll(Arrays.asList(p1, p2));
		
		
		
		
	}

}
