package com.resonance.api.request;

import java.util.Map;

import com.resonance.api.constants.Constants;

import okhttp3.Request;

/**
 * Builds okhttp3 Request objects for use with the last.fm API server
 *
 * @author Drew
 */
public class RequestBuilder {

  public RequestBuilder() {}

  public Request buildArtistGetInfoRequest(Map<String, String> parameters) {
    if (!parameters.containsKey(Constants.ARTIST)) {
      try {
        throw new Exception();
      } catch (Exception e) {
        System.out.println("INVALID PARAMETERS IN REQUEST: MISSING ARTIST");
        e.printStackTrace();
        return null;
      }
    }

    String url = Constants.ROOTURL;
    url += Constants.ARTIST_GET_INFO;
    url += parameters.get(Constants.ARTIST);
    url += Constants.API_KEY;
    url += Constants.JSON_FORMAT;

    return new Request.Builder().url(url).build();
  }

  public Request buildTrackGetInfoRequest(Map<String, String> parameters) {
    if (!parameters.containsKey(Constants.ARTIST) || !parameters.containsKey(Constants.TRACK)) {
      try {
        throw new Exception();
      } catch (Exception e) {
        System.out.println("INVALID PARAMETERS IN REQUEST: MISSING ARTIST");
        e.printStackTrace();
        return null;
      }
    }

    String url = Constants.ROOTURL;
    url += Constants.TRACK_GET_INFO;
    url += Constants.API_KEY;
    url += "&artist=" + parameters.get(Constants.ARTIST);
    url += "&track=" + parameters.get(Constants.TRACK);
    url += Constants.JSON_FORMAT;

    return new Request.Builder().url(url).build();
  }
  
  public Request buildTrackGetSimilarRequest(Map<String, String> parameters) {
    if(!parameters.containsKey(Constants.ARTIST) ||
       !parameters.containsKey(Constants.TRACK)) {
      try {
        throw new Exception();
      } catch (Exception e) {
        System.out.println("INVALID PARAMETERS IN REQUEST: MISSING ARTIST");
        e.printStackTrace();
        return null;
      }
    }
    
    String url = Constants.ROOTURL;
    url += Constants.TRACK_GET_SIMILAR;
    url += parameters.get(Constants.ARTIST);
    url += "&track=" + parameters.get(Constants.TRACK);
    url += Constants.API_KEY;
    url += Constants.JSON_FORMAT;
    
    return new Request.Builder().url(url).build();
  }
}
