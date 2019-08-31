package com.resonance.main;

import java.util.Map;

import com.resonance.api.constants.Constants;
import com.resonance.api.request.RequestBuilder;
import com.resonance.api.response.ResponseParser;
import com.resonance.marshalling.TrackParser;
import com.resonance.model.simple.Track;

public class TrackCreator {
  private RequestBuilder builder = new RequestBuilder();
  private ResponseParser responseParser = new ResponseParser();

  public TrackCreator() {}
  /**
   * Create a new Track object
   *
   * @param parameters - REQUIRED KEYS: Constants.ARTIST_ID, Constants.ARTIST, Constants.TRACK
   * @return Track
   */
  public Track createTrack(Map<String, String> parameters) {
    long id = 0;
    try {
      id = Long.parseLong(parameters.get(Constants.ARTIST_ID));
    } catch (NumberFormatException e) {
      System.out.println("Improper Track ID parameter in createArtist");
      e.printStackTrace();
      return null;
    }

    responseParser.setRequest(builder.buildTrackGetInfoRequest(parameters));
    String response = responseParser.parseResponse();
    TrackParser trackParser = new TrackParser();
    Track track = trackParser.parseJSON(response, id);
    return track;
  }
}
