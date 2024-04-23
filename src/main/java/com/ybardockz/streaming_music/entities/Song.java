package com.ybardockz.streaming_music.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id_song")
	private Long id;
	private String name;
	private Double duration;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idalbum")
	private Album album;
	
	@ManyToMany(mappedBy = "songs")
	private List<Playlist> playlists = new ArrayList<>();

	public Song() {
		
	}

	public Song(Long id, String name, Double duration, Album album) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	public void removePlaylist(Playlist playlist) {
		playlists.remove(playlist);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(id, other.id);
	}
	
}
