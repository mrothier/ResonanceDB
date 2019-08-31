package com.resonance.main;

import java.util.Map;

import com.resonance.api.request.RequestBuilder;
import com.resonance.api.response.ResponseParser;
import com.resonance.marshalling.SimilarTrackListParser;
import com.resonance.model.simple.SimilarTrackList;

public class SimilarTrackListCreator {
  private RequestBuilder builder = new RequestBuilder();
  private ResponseParser responseParser = new ResponseParser();
  public SimilarTrackListCreator() {}

  public SimilarTrackList createSimilarTrack(Map<String, String> parameters) {
    
    responseParser.setRequest(builder.buildTrackGetSimilarRequest(parameters));
    String response = responseParser.parseResponse();
    SimilarTrackListParser similarTrackParser = new SimilarTrackListParser();
    SimilarTrackList similarTrack = similarTrackParser.parseJSON(response);
    return similarTrack;
  }

}
