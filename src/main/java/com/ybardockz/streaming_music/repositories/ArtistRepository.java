package com.ybardockz.streaming_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ybardockz.streaming_music.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
