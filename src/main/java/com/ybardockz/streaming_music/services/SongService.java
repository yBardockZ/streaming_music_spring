package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.repositories.SongRepository;
import com.ybardockz.streaming_music.services.exceptions.DbException;
import com.ybardockz.streaming_music.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongService {

	private SongRepository repository;

	public SongService(SongRepository repository) {
		this.repository = repository;
	}

	public Song findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Song> findAll() {
		Sort sort = Sort.by("name").descending();
		return repository.findAll(sort);
	}

	public void insert(Song song) {
		repository.save(song);
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

	public void update(Song song, Long id) {
		try {
			Song songExisting = findById(id);
			songExisting.setName(song.getName());
			songExisting.setDuration(song.getDuration());

			repository.save(songExisting);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
