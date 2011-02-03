package com.jesper.music.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.force.sdk.jpa.annotation.CustomField;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

    @CustomField(externalId=true)
    private String freebaseId;

    private String name;
    
    private String origin;
    
    @DateTimeFormat(iso=ISO.DATE)
    private Date activeStart;
    
    @DateTimeFormat(iso=ISO.DATE)
    private Date activeEnd;
    
    @OneToMany(mappedBy="artist")
    private List<Album> albums;

    
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreebaseId() {
		return freebaseId;
	}

	public void setFreebaseId(String freebaseId) {
		this.freebaseId = freebaseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Date getActiveStart() {
		return activeStart;
	}

	public void setActiveStart(Date activeStart) {
		this.activeStart = activeStart;
	}

	public Date getActiveEnd() {
		return activeEnd;
	}

	public void setActiveEnd(Date activeEnd) {
		this.activeEnd = activeEnd;
	}
    
    

}
