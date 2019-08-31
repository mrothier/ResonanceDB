package com.resonance.marshalling;

import com.google.gson.Gson;
import com.resonance.model.simple.Track;

public class TrackParser {
  public TrackParser() {}

  public Track parseJSON(String response, long id) {
    Gson gson = new Gson();
    
    TrackWrapper trackWrapper = gson.fromJson(response, TrackWrapper.class);
    Track track = trackWrapper.track;
    track.setID(id);
    return track;
  }
  
  static class TrackWrapper{
    Track track;
  }
}
