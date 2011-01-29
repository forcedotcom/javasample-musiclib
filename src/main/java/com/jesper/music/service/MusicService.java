package com.jesper.music.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesper.music.model.Album;
import com.jesper.music.model.Artist;
import com.jesper.music.model.Genre;

@Service
public class MusicService {
	
	private static final Logger logger = LoggerFactory.getLogger(MusicService.class);

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List getList(String entity, String query) {
		if(query!=null) {
			// This is clearly just for playing around. Wide open to JPQL injection.
			return em.createQuery("SELECT o FROM "+entity+" o WHERE "+query).getResultList();
		} else {
			return em.createQuery("SELECT o FROM "+entity+" o").getResultList();
		}
	}

	@Transactional(readOnly=true)
	public Album findAlbumById(String id) {
		try {
			Album a = em.find(Album.class, id);
			if(a==null) {
				a = new Album();
			}
			return a;
		}
		catch(Exception e) {
			logger.info("find threw exception: "+e+": "+e.getMessage());
			logger.info("Will return null");
			return null;
		}
	}

	@Transactional
	public void createAlbum(Album album) {
		// TODO Auto-generated method stub
		em.persist(album.getArtist());
		em.persist(album.getGenre());
		em.persist(album);
	}

	@Transactional
	public void updateAlbum(Album album) {
		// TODO Auto-generated method stub
		logger.info("Date type: "+album.getReleaseDate().getClass());
		em.merge(album);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findEntity(Class clazz, String id) {
		try {
			Object o = em.find(clazz, id);
			if(o==null) {
				o = clazz.newInstance();
			}
			return o;
		}
		catch(Exception e) {
			logger.info("find threw exception: "+e+": "+e.getMessage());
			logger.info("Will return null");
			return null;
		}
	}

	@Transactional
	public String saveAlbum(Album album) {
		if(album.getArtist().getId()==null) {
			em.persist(album.getArtist());
		}
		if(album.getGenre().getId()==null) {
			em.persist(album.getGenre());
		}
		if(album.getId()==null) {
			em.persist(album);
		} else {
			em.merge(album);
		}
		return album.getId();
	}

	@Transactional
	public String saveGenre(Genre genre) {
		if(genre.getId()==null) {
			em.persist(genre);
		} else {
			em.merge(genre);
		}
		return genre.getId();
	}

	@Transactional
	public String saveArtist(Artist artist) {
		if(artist.getId()==null) {
			em.persist(artist);
		} else {
			em.merge(artist);
		}
		return artist.getId();
	}


}
