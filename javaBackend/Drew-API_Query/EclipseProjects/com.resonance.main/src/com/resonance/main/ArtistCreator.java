package com.resonance.main;

import java.util.Map;

import com.resonance.api.constants.Constants;
import com.resonance.api.request.RequestBuilder;
import com.resonance.api.response.ResponseParser;
import com.resonance.marshalling.ArtistParser;
import com.resonance.model.simple.Artist;

public class ArtistCreator {
  private RequestBuilder builder = new RequestBuilder();
  private ResponseParser responseParser = new ResponseParser();

  public ArtistCreator() {}

  /**
   * Create a new Artist object
   *
   * @param parameters - REQUIRED KEYS: Constants.ARTIST_ID, Constants.ARTIST
   * @return Artist
   */
  public Artist createArtist(Map<String, String> parameters) {
    long id = 0;
    try {
      id = Long.parseLong(parameters.get(Constants.ARTIST_ID));
    } catch (NumberFormatException e) {
      System.out.println("Improper Artist ID parameter in createArtist");
      e.printStackTrace();
      return null;
    }
    responseParser.setRequest(builder.buildArtistGetInfoRequest(parameters));
    String response = responseParser.parseResponse();
    ArtistParser artistParser = new ArtistParser();
    Artist artist = artistParser.parseJSON(response, id);
    return artist;
  }
}
