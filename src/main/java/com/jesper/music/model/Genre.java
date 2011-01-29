package com.jesper.music.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.force.sdk.jpa.annotation.CustomField;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

    private String name;
    
    @CustomField(externalId=true)
    private String freebaseId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFreebaseId() {
		return freebaseId;
	}

	public void setFreebaseId(String freebaseId) {
		this.freebaseId = freebaseId;
	}

    
}
