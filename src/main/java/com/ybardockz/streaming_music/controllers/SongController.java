package com.ybardockz.streaming_music.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ybardockz.streaming_music.entities.Song;
import com.ybardockz.streaming_music.services.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

	private SongService service;

	public SongController(SongService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Song>> findAll() {
		List<Song> songs = service.findAll();
		return ResponseEntity.ok().body(songs);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Song> findById(@PathVariable Long id) {
		Song song = service.findById(id);

		return ResponseEntity.ok().body(song);
	}

	@PostMapping
	public ResponseEntity<Song> insert(@RequestBody Song song) {
		service.insert(song);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(song.getId()).toUri();

		return ResponseEntity.created(uri).body(song);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Song> update(@RequestBody Song song, @PathVariable Long id) {
		service.update(song, id);

		return ResponseEntity.ok().body(song);
	}

}
