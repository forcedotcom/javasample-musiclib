package com.jesper.music.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    private Date activeStart;
    
    private Date activeEnd;

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
