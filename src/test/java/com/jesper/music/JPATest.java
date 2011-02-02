package com.jesper.music;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jesper.music.model.Album;
import com.jesper.music.model.Artist;
import com.jesper.music.model.Genre;

public class JPATest {

	private static ApplicationContext context = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app-context.xml");
	}

	@Test
	public void testCreate() {
		
		EntityManager em = getEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		
		Album album = new Album();
		Artist artist = new Artist();
		Genre genre = new Genre();
		
		genre.setName("R&B Dance");
		
		artist.setName("The Black Eyed Peas");
		artist.setOrigin("Los Angeles");
		Calendar cal = Calendar.getInstance();
		cal.set(1990,0,1);
		artist.setActiveStart(cal.getTime());
		
		album.setName("The Beginning");
		album.setArtist(artist);
		album.setGenre(genre);
		cal.set(2010,11,1);
		album.setReleaseDate(cal.getTime());

		em.persist(artist);
		em.persist(genre);
		em.persist(album);

		tx.commit();
		
		String albumId = album.getId();
		String genreId = genre.getId();
		String artistId = artist.getId();

		tx = em.getTransaction();

		tx.begin();

		album = em.find(Album.class, albumId);
		
		assertEquals(album.getName(),"The Beginning");
		
		em.remove(album);
		
		em.remove(em.find(Genre.class,genreId));
		
		em.remove(em.find(Artist.class, artistId));

		tx.commit();
		em.close();
	}

	@Test
	public void testUpdate() {
		
		EntityManager em = getEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		
		Album album = new Album();
		
		Calendar cal = Calendar.getInstance();

		album.setName("The Beginning");
		cal.set(2010,11,1);
		album.setReleaseDate(cal.getTime());

		em.persist(album);

		tx.commit();
		
		String albumId = album.getId();

		tx = em.getTransaction();

		tx.begin();

		album = em.find(Album.class, albumId);
		
		assertEquals(album.getName(),"The Beginning");

		cal.set(2010,8,1);

		album.setReleaseDate(cal.getTime());
		
		em.merge(album);
		
		tx.commit();

		tx = em.getTransaction();

		tx.begin();

		//em.remove(em.find(Album.class, albumId));
		
		tx.commit();
		em.close();
	}

	@Test
	public void testUpdateWithTransactionAnnotation() {
		
		EntityManager em = getEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		
		Album album = new Album();
		
		Calendar cal = Calendar.getInstance();

		album.setName("The Beginning");
		cal.set(2010,11,1);
		album.setReleaseDate(cal.getTime());

		em.persist(album);

		tx.commit();
		
		String albumId = album.getId();

		tx = em.getTransaction();

		tx.begin();

		album = em.find(Album.class, albumId);
		
		assertEquals(album.getName(),"The Beginning");

		cal.set(2010,8,1);

		album.setReleaseDate(cal.getTime());
		
		em.merge(album);
		
		tx.commit();

		tx = em.getTransaction();

		tx.begin();

		//em.remove(em.find(Album.class, albumId));
		
		tx.commit();
		em.close();
	}
	

	private static EntityManager getEntityManager() {
		EntityManagerFactory emFactory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		EntityManager em = emFactory.createEntityManager();
		return em;
	}

}


