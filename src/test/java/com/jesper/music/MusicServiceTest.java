package com.jesper.music;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.jesper.music.model.Album;
import com.jesper.music.service.MusicService;

public class MusicServiceTest {

	
	private EntityManager mockEntityManagerForQuery(final String query, final List result) {

		EntityManager em = mock(EntityManager.class);
		//when(em.createQuery("SELECT o FROM wonderland o WHERE o.name = 'Alice'")).thenReturn(q);
		when(em.createQuery(anyString())).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {

				// We pass null when we're not testing that MusicService is doing the correct query.
				if(query!=null) {
					assertEquals(query,invocation.getArguments()[0].toString());
				}
				Query q = mock(Query.class);
				when(q.getResultList()).thenReturn(result);
				return q;
		    }
		});
		
		return em;
	}
	
	@Test
	public void testGetListWithQuery() {

		//Setup
		MusicService service = new MusicService();
		List<String> l = new ArrayList<String>();
		l.add("Expected Result");
		service.setEntityManager(mockEntityManagerForQuery("SELECT o FROM wonderland o WHERE o.name = 'Alice'", l));
				
		//Run
		assertEquals("Expected Result",(String) service.getList("wonderland", "o.name = 'Alice'").get(0));
		
	}

	@Test
	public void testGetListWithoutQuery() {

		//Setup
		MusicService service = new MusicService();
		List<String> l = new ArrayList<String>();
		l.add("Expected Result");
		service.setEntityManager(mockEntityManagerForQuery("SELECT o FROM wonderland o", l));
				
		//Run
		assertEquals("Expected Result",(String) service.getList("wonderland", null).get(0));
		
	}

	@Test
	public void testGetListWithNullEntity() {

		//Setup
		MusicService service = new MusicService();
		List<String> l = new ArrayList<String>();
		l.add("Expected Result");
		service.setEntityManager(mockEntityManagerForQuery(null, l));

		//Run
		try {
			service.getList(null,null);
		} catch(IllegalArgumentException e) {
			return;
		}
		fail("service.getList did not throw IllegalArgumentException as expected when passed a null entity");
	}
	
	@Test
	public void testfindValidEntity() {
		Album a = new Album();
		EntityManager em = mock(EntityManager.class);
		when(em.find(Album.class, "new")).thenReturn(null);
		when(em.find(Album.class, "a_valid_id")).thenReturn(new Album());
		when(em.find(Album.class, "an_unknown_id")).thenReturn(null);

		
	}

}
