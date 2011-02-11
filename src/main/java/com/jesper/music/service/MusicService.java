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
	
	static String QUERY_WITH_WHERE = "SELECT o FROM %s o WHERE %s";
	static String QUERY_WITHOUT_WHERE = "SELECT o FROM %s o";

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List getList(String entity, String query) {
		if(entity==null) {
			throw new IllegalArgumentException("entity cannot be null");
		}
		List l = null;
		long t=System.currentTimeMillis();
		if(query!=null) {
			// This is clearly just for playing around. Wide open to JPQL injection.
			l = em.createQuery(String.format(QUERY_WITH_WHERE,entity,query)).getResultList();
		} else {
			l = em.createQuery(String.format(QUERY_WITHOUT_WHERE,entity)).getResultList();
		}
		logger.info("Took "+(System.currentTimeMillis()-t)+" millis");
		return l;
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
	public Album saveAlbum(Album album) {
		if(album.getArtist()!=null && album.getArtist().getId()==null) {
			em.persist(album.getArtist());
		}
		if(album.getGenre()!=null && album.getGenre().getId()==null) {
			em.persist(album.getGenre());
		}
		if(album.getId()==null) {
			em.persist(album);
		} else {
			album = em.merge(album);
		}
		return album;
	}

	@Transactional
	public Genre saveGenre(Genre genre) {
		if(genre.getId()==null) {
			em.persist(genre);
		} else {
			genre = em.merge(genre);
		}
		return genre;
	}

	@Transactional
	public Artist saveArtist(Artist artist) {
		if(artist.getId()==null) {
			em.persist(artist);
		} else {
			artist =  em.merge(artist);
		}
		return artist;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


}
