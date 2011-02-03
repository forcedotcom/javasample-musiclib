package com.jesper.music;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.jesper.music.model.Album;
import com.jesper.music.model.Artist;
import com.jesper.music.model.Genre;
import com.jesper.music.service.MusicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class MusicServiceTest {

	@Inject
    private ApplicationContext context;
	
	@Inject
	private MusicService musicService;
	
	@PersistenceContext
	private EntityManager em;
	
	private String testAlbumId;
	private String testArtistId;
	
	@BeforeTransaction
	public void createTestData() {
		EntityManagerFactory emFactory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		EntityManager mgr = emFactory.createEntityManager();
		
		EntityTransaction tx = mgr.getTransaction();

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

		mgr.persist(artist);
		mgr.persist(genre);
		mgr.persist(album);
		
		tx.commit();

		testAlbumId = album.getId();
		testArtistId = artist.getId();
	}
	
	@AfterTransaction
	public void deleteTestData() {
		EntityManagerFactory emFactory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		EntityManager mgr = emFactory.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		tx.begin();
		
		Album a = mgr.find(Album.class, testAlbumId);
		//Genre g = em.find(Genre.class, a.getGenre().getId());
		//Artist ar = em.find(Artist.class, a.getArtist().getId());
		mgr.remove(a);
		//em.remove(g);
		//em.remove(ar);
		tx.commit();
	}
	
		
	@Test
	@Transactional(readOnly=true)
	public void testFind() {
		assertEquals("The Beginning",musicService.findAlbumById(testAlbumId).getName());
	}

	@Test
	@Transactional(readOnly=true)
	public void testGetRelated() {
		Artist a = (Artist) musicService.findEntity(Artist.class, testArtistId);
		a.getAlbums();
	}

//	@Test
//	@Transactional(readOnly=true)
//	public void testFind() {
//		assertEquals("The Beginning",musicService.findAlbumById(testAlbumId).getName());
//	}
	
	
	
	
//	
//	@Test
//	@Transactional(readOnly=true) 
//	public void get100Records() {
//		long t = System.currentTimeMillis();
//		List<Album> l = em.createQuery("SELECT a FROM Album a",Album.class).getResultList();
//		System.out.println("Retrieved "+l.size()+" in "+(System.currentTimeMillis()-t)+" millis");
//	}
	
//	@Test
//	public void get100NoTx() {
//		long t = System.currentTimeMillis();
//		EntityManagerFactory emFactory = context.getBean("entityManagerFactory", EntityManagerFactory.class);
//		EntityManager mgr = emFactory.createEntityManager();
//		System.out.println("Got entity manager in "+(System.currentTimeMillis()-t)+" millis");
//		t = System.currentTimeMillis();
//		EntityTransaction tx = mgr.getTransaction();
//		tx.begin();
//		System.out.println("Opened transaction in "+(System.currentTimeMillis()-t)+" millis");
//		t = System.currentTimeMillis();
//		List<Album> l = em.createQuery("SELECT a FROM Album a",Album.class).getResultList();
//		System.out.println("Retrieved "+l.size()+" in "+(System.currentTimeMillis()-t)+" millis");
//		t = System.currentTimeMillis();
//		
//		tx.commit();
//		System.out.println("Committed transaction in "+(System.currentTimeMillis()-t)+" millis");
//	}
	
//	@Test
//	public void get100Records2() {
//		long t = System.currentTimeMillis();
//		innerGet100Records2();
//		System.out.println("Get2 took "+(System.currentTimeMillis()-t)+" millis");
//	}
//
//	@Transactional(readOnly=true) 
//	private void innerGet100Records2() {
//		long t = System.currentTimeMillis();
//		List<Album> l = em.createQuery("SELECT a FROM Album a",Album.class).getResultList();
//		System.out.println("InnerGet2 got "+l.size()+" records in "+(System.currentTimeMillis()-t)+" millis");
//	}
}
