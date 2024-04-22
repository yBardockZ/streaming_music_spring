package com.ybardockz.streaming_music.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.repositories.SongRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongService {

	private SongRepository repository;

	public SongService(SongRepository repository) {
		this.repository = repository;
	}

	public Song findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Song not found"));
	}

	public List<Song> findAll() {
		Sort sort = Sort.by("name").descending();
		return repository.findAll(sort);
	}

	public void insert(Song song) {
		repository.save(song);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void update(Song song, Long id) {
		try {
			Song songExisting = findById(id);
			songExisting.setName(song.getName());
			songExisting.setDuration(song.getDuration());

			repository.save(songExisting);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
