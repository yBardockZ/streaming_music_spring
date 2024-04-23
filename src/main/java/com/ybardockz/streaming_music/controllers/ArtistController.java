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

import com.ybardockz.streaming_music.entities.Artist;
import com.ybardockz.streaming_music.services.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
	
	private ArtistService service;
	
	public ArtistController(ArtistService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Artist>> findAll() {
		List<Artist> artists = service.findAll();
		return ResponseEntity.ok().body(artists);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Artist> findById(@PathVariable Long id) {
		Artist artist = service.findById(id);
		
		return ResponseEntity.ok().body(artist);
	}
	
	@PostMapping
	public ResponseEntity<Artist> insert(@RequestBody Artist artist) {
		service.insert(artist);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(artist.getId()).toUri();
		
		return ResponseEntity.created(uri).body(artist);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Artist> update(@RequestBody Artist artist, @PathVariable Long id) {
		service.update(artist, id);
			
		return ResponseEntity.ok().body(artist);
	}

}
