package com.resonance.marshalling;

import com.google.gson.Gson;
import com.resonance.model.simple.SimilarTrack;
import com.resonance.model.simple.SimilarTrackList;

public class SimilarTrackListParser {

  public SimilarTrackListParser() {}

  public SimilarTrackList parseJSON(String response) {
    Gson gson = new Gson();

    TrackWrapper trackWrapper = gson.fromJson(response, TrackWrapper.class);
    SimilarTrackList trackList = new SimilarTrackList(trackWrapper.similartracks.track);
    return trackList;
  }

  static class TrackWrapper {
    SimilarTrackArray similartracks;
  }
  
  static class SimilarTrackArray{
    SimilarTrack[] track;
  }
}
