/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import java.util.ArrayList;

public class MockMp3Player implements Mp3Player {

	//protected ArrayList PlayList = new ArrayList();
	ArrayList PlayList;
	int crt_song;
	boolean is_playing;
	double position;
  /** 
   * Begin playing the filename at the top of the
   * play list, or do nothing if playlist 
   * is empty. 
   */
  public void play() {
	  if (PlayList!=null) {
		  if (PlayList.size()!=0) {
			  is_playing= true;
			  position=0.1;
		  }
	  }
	  else {
		  is_playing=false;
	  }
  }

  /** 
   * Pause playing. Play will resume at this spot. 
   */
  public void pause() {is_playing=false;}

  /** 
   * Stop playing. The current song remains at the
   * top of the playlist, but rewinds to the 
   * beginning of the song.
   */
  public void stop() {is_playing=false; position = 0.0;}
  
  /** Returns the number of seconds into 
   * the current song.
   */
  public double currentPosition(){ return position;}


  /**
   * Returns the currently playing file name.
   */
  public String currentSong() {
	  if (PlayList.size()>0){
		if (crt_song > PlayList.size()-1) {crt_song=0; }  //repair position 
		return (String) PlayList.get(crt_song);
	  }
	  else {
		  return null;
	  	}
  }

  /** 
   * Advance to the next song in the playlist 
   * and begin playing it.
   */
  public void next() {
	  if (crt_song < PlayList.size()-1) {
		  crt_song+=1;
	  } // it will play last song if there is no next song ( not circular)
	  
	  is_playing=true;
  }

  /**
   * Go back to the previous song in the playlist
   * and begin playing it.
   */
  public void prev() {
	  if(crt_song!=0) {
		  crt_song-=1;
	  }// it will play first song if there is no previous song (not circular)
	  
	  is_playing=true; 
  }

  /** 
   * Returns true if a song is currently 
   * being played.
   */
  public boolean isPlaying() {return is_playing;}

  /**
   * Load filenames into the playlist.
   */
  public void loadSongs(ArrayList names) {
	  String a_song;
	  
	  if (names.size() >0) {
		  if(PlayList==null) {PlayList = new ArrayList();}
		  for (int i=0; i < names.size(); i++) {
			  a_song=(String) names.get(i);
			  PlayList.add( a_song);
			}
	  }
	  
	  crt_song=0;
	  is_playing=false;   
	  position = 0.0;
  }
}
