package com.ybardockz.streaming_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ybardockz.streaming_music.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
