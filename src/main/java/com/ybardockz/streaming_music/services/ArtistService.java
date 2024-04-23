package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.Artist;
import com.ybardockz.streaming_music.repositories.ArtistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArtistService {

	private ArtistRepository repository;

	public ArtistService(ArtistRepository repository) {
		this.repository = repository;
	}
	
	public Artist findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist not found"));
	}
	
	public List<Artist> findAll() {
		Sort sort = Sort.by("name").descending();
		return repository.findAll(sort);
	}
	
	public void insert(Artist artist) {
		repository.save(artist);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void update(Artist artist, Long id) {
		try {
			Artist artistExisting = findById(id);
			artistExisting.setBio(artist.getBio());
			artistExisting.setName(artist.getName());
			
			repository.save(artistExisting);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
