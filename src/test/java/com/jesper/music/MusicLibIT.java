package com.jesper.music;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.force.sdk.connector.ForceServiceConnector;
import com.jesper.music.model.Album;
import com.jesper.music.service.MusicService;
import com.sforce.ws.ConnectionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class MusicLibIT {

	@Inject
	private MusicService musicService;
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private ForceServiceConnector connector;
	
	@Test
	public void testFindAlbumWithNewAsId() {
		
		// MusicService currently allows this. It expects a null return value.
		Album a = (Album) musicService.findEntity(Album.class, "new");
		assertNotNull(a);
		assertNull(a.getId());
	}

	@Test
	public void testAlbumCUDNoRelationships() {
		
		// MusicService currently allows this. It expects a null return value.
		
		String albumId = null;

		try {
			connector.getConnection().getConfig().setTraceMessage(true);
			connector.getConnection().getConfig().setPrettyPrintXml(true);

			Album album = new Album();
			album.setName("The Beginning");

			// Here I want to just set the album release date. Don't care about time. My approach
			// was to use the set method on Calendar. Dunno if there are better ways.
			
			Calendar cal = Calendar.getInstance();

			// Initially I was not calling clear(), If you call clear(), life is good in Pacific time zone
			// because midnight pacific is same calendar date as zulu (8am), but if you're in Copenhagen,
			// life is not so good. Midnight on Dec 1 in Copenhagen is Nov 30th Zulu.
			cal.clear();

			// Sets date to Dec 1, 2010.
			cal.set(2010,11,1);
			
			// releaseDate is of type "date" meaning it shouldn't care about time. But Java type is java.util.Date
			album.setReleaseDate(cal.getTime());
	
			// When trace is on, notice the full time stamp format of the date in the SOAP message
			musicService.saveAlbum(album);
			
			assertNotNull(album.getId());
			albumId = album.getId();

			// When trace is on, you'll see the SOAP message coming back from this call is simply yyyy-MM-dd,
			// which seems inconsistent with how we posted it just before.
			album = (Album) musicService.findEntity(Album.class,album.getId());
			
			System.out.println(album.getReleaseDate());
			assertEquals("The Beginning",album.getName());

			// Just for the paranoid, let's print the full date string of the cal object from above
			System.out.println(cal.getTime().toString());
			
			// Now let's set it to the date coming from the retrieved album record
			cal.setTime(album.getReleaseDate());
			
			// ...and print it.
			System.out.println(cal.getTime().toString());

			// some assertions
			assertEquals("Comparing release date year",2010,cal.get(Calendar.YEAR));
			assertEquals("Comparing release date month", 11,cal.get(Calendar.MONTH));
			assertEquals("Comparing release date day of month",1,cal.get(Calendar.DAY_OF_MONTH));

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(albumId!=null) {
				try {
					connector.getConnection().delete(new String[] {albumId });
				} catch (ConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
