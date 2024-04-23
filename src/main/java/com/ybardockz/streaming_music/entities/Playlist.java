package com.ybardockz.streaming_music.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "playlists")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "playlist_song",
			joinColumns = @JoinColumn(name = "idplaylist"),
			inverseJoinColumns = @JoinColumn(name = "idsong"))
	private List<Song> songs = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	public Playlist() {
		
	}

	public Playlist(String name, List<Song> songs, User user) {
		this.name = name;
		this.songs = songs;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public Long getId() {
		return id;
	}
	
	public void addSongs(Song song) {
		songs.add(song);
	}
	
	public void removeSongs(Song song) {
		songs.remove(song);
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
		Playlist other = (Playlist) obj;
		return Objects.equals(id, other.id);
	}

}
