package com.ybardockz.streaming_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ybardockz.streaming_music.entities.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
