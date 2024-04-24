package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.Artist;
import com.ybardockz.streaming_music.repositories.ArtistRepository;
import com.ybardockz.streaming_music.services.exceptions.DbException;
import com.ybardockz.streaming_music.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArtistService {

	private ArtistRepository repository;

	public ArtistService(ArtistRepository repository) {
		this.repository = repository;
	}
	
	public Artist findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Artist> findAll() {
		Sort sort = Sort.by("name").descending();
		return repository.findAll(sort);
	}
	
	public void insert(Artist artist) {
		repository.save(artist);
	}
	
	public void delete(Long id) {
		try {
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	public void update(Artist artist, Long id) {
		try {
			Artist artistExisting = findById(id);
			artistExisting.setBio(artist.getBio());
			artistExisting.setName(artist.getName());
			
			repository.save(artistExisting);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}

}
