/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import static org.easymock.EasyMock.*;
import junit.framework.*;
import java.util.ArrayList;

import org.easymock.EasyMock;

public class TestEasyMockMp3Player extends TestCase {
  
  private Mp3Player mp3;
  private Mp3Player mp3_ctrl;
  EasyMockMp3Player em;
  
  protected ArrayList list = new ArrayList();
  
  @Override
  protected void setUp() {
    

	// Create a control handle to the Mock object
	mp3_ctrl = createMock(Mp3Player.class);
	mp3 = mp3_ctrl;  
    em = new EasyMockMp3Player();
    em.addListener(mp3);

    list = new ArrayList();
    list.add("Bill Chase -- Open Up Wide");
    list.add("Jethro Tull -- Locomotive Breath");
    list.add("The Boomtown Rats -- Monday");
    list.add("Carl Orff -- O Fortuna");
  }

  public void testPlay() {	  
	  	  
	    mp3.loadSongs(list); //EasyMock.expectLastCall(); //mp3.loadSongs(list);
	    expect(mp3.isPlaying()).andReturn(false);
	    mp3.play();
	    expect(mp3.isPlaying()).andReturn(true);
	    expect(mp3.currentPosition()).andReturn(0.1);
	    mp3.pause();
	    expect(mp3.currentPosition()).andReturn(0.1);
	    mp3.stop();
	    expect(mp3.currentPosition()).andReturn(0.1);
	    replay(mp3 );
	
	em.run_loadSongs(list); 
    assertFalse(em.run_isPlaying());
    em.run_play();
    assertTrue(em.run_isPlaying());
    assertTrue(em.run_currentPosition() != 0.0);
    em.run_pause();
    assertTrue(em.run_currentPosition() != 0.0);
    em.run_stop();
    assertEquals(em.run_currentPosition(), 0.0, 0.1);
    
    verify(mp3);
  }

  public void testPlayNoList() {

    // Don't set the list up
	expect(mp3.isPlaying()).andReturn(false);
	mp3.play();
    expect(mp3.isPlaying()).andReturn(false);	
	expect(mp3.currentPosition()).andReturn(0.1);
	mp3.pause();
	expect(mp3.currentPosition()).andReturn(0.1);
	expect(mp3.isPlaying()).andReturn(false);	
	mp3.stop();
	expect(mp3.currentPosition()).andReturn(0.1);
	expect(mp3.isPlaying()).andReturn(false);
	
	replay(mp3_ctrl );
	
	assertFalse(em.run_isPlaying());
    em.run_play();
	assertFalse(em.run_isPlaying());
	assertEquals(em.run_currentPosition(), 0.0, 0.1);
	em.run_pause();
	assertEquals(em.run_currentPosition(), 0.0, 0.1);
	assertFalse(em.run_isPlaying());
	em.run_stop();
	assertEquals(em.run_currentPosition(), 0.0, 0.1);
	assertFalse(em.run_isPlaying());
	
	
    verify(mp3);
	
  }

  public void testAdvance() {
	    mp3.loadSongs(list);
	    mp3.play();
	    expect(mp3.isPlaying()).andReturn(true);
	    mp3.prev();
	    expect(mp3.currentSong()).andReturn("Bill Chase -- Open Up Wide");			//list.get(0));
	    expect(mp3.isPlaying()).andReturn(true);
	    mp3.next();
	    expect( mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");    //list.get(1));
	    mp3.next();
	    expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");          //list.get(2));
	    mp3.prev();

	    expect(mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");      //list.get(1));
	    mp3.next();
	    expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");           //list.get(2));
	    mp3.next();
	    expect(mp3.currentSong()).andReturn("Carl Orff -- O Fortuna");           //list.get(3));
	    mp3.next();
	    expect(mp3.currentSong()).andReturn("Carl Orff -- O Fortuna");          //list.get(3));
	    expect(mp3.isPlaying()).andReturn(true);

		replay(mp3_ctrl );
 
    em.run_loadSongs(list);
    em.run_play();

    assertTrue(em.run_isPlaying());

    em.run_prev();
    assertEquals(em.run_currentSong(), list.get(0));
    assertTrue(em.run_isPlaying());

    em.run_next();
    assertEquals(em.run_currentSong(), list.get(1));
    em.run_next();
    assertEquals(em.run_currentSong(), list.get(2));
    em.run_prev();

    assertEquals(em.run_currentSong(), list.get(1));
    em.run_next();
    assertEquals(em.run_currentSong(), list.get(2));
    em.run_next();
    assertEquals(em.run_currentSong(), list.get(3));
    em.run_next();
    assertEquals(em.run_currentSong(), list.get(3));
    assertTrue(em.run_isPlaying());
    
    verify(mp3);

  }

}
