/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class EasyMockMp3Player {
	
    private final Set<Mp3Player> listeners = new HashSet<Mp3Player>();

    public void addListener(final Mp3Player listener) {
        listeners.add(listener);
    }
	//protected ArrayList PlayList = new ArrayList();
	ArrayList PlayList;
	int crt_song;
	boolean is_playing;
	double position;
	public void run_play() { 
        for (final Mp3Player listener : listeners) {
            listener.play();
        }
	}
    public void run_pause(){
        for (final Mp3Player listener : listeners) {
            listener.pause();
        }   	
    }
    public void run_stop(){
        for (final Mp3Player listener : listeners) {
            listener.stop();
        }   	
    }
   public double run_currentPosition(){
       for (final Mp3Player listener : listeners) {
           return listener.currentPosition();
       }   	
       return 0.1;
   }
  public String run_currentSong(){
      for (final Mp3Player listener : listeners) {
          return listener.currentSong();
      }   	
      return "Bill Chase -- Open Up Wide";
  }
   public void run_next(){
       for (final Mp3Player listener : listeners) {
           listener.next();
       }   	
   }
    public void run_prev(){
        for (final Mp3Player listener : listeners) {
            listener.prev();
        }   	
    }
    public boolean run_isPlaying(){
        for (final Mp3Player listener : listeners) {
            return listener.isPlaying();
        }   
        return true;
    }
    public void run_loadSongs(ArrayList names){
    	ArrayList list = new ArrayList();
        list.add("Bill Chase -- Open Up Wide");
        list.add("Jethro Tull -- Locomotive Breath");
        list.add("The Boomtown Rats -- Monday");
        list.add("Carl Orff -- O Fortuna");
        for (final Mp3Player listener : listeners) {
            listener.loadSongs(list);
        }   	
    }
     
  }
