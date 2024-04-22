package com.ybardockz.streaming_music.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Albums")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id_album")
	private Long id;
	private String title;
	private LocalDate releaseDate;
	
	@OneToMany(mappedBy = "album")
	private List<Song> song = new ArrayList<>();

	public Album() {
	}

	public Album(String title, LocalDate releaseDate) {
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Song> getSong() {
		return song;
	}
	
	public void addSong(Song song) {
		this.song.add(song);
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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
		Album other = (Album) obj;
		return Objects.equals(id, other.id);
	}
	
}
