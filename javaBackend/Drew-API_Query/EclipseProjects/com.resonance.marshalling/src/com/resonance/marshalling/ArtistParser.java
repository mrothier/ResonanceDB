package com.resonance.marshalling;

import com.google.gson.Gson;
import com.resonance.model.simple.Artist;

public class ArtistParser {
  public ArtistParser() {}

  public Artist parseJSON(String response, long id) {
    Gson gson = new Gson();

    ArtistWrapper artistWrapper = gson.fromJson(response, ArtistWrapper.class);
    Artist artist = artistWrapper.artist;
    artist.setID(id);
    return artist;
  }

  static class ArtistWrapper {
    Artist artist;
  }
}
