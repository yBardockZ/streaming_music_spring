package com.ybardockz.streaming_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ybardockz.streaming_music.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
