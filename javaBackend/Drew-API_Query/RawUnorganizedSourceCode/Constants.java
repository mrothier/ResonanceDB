package com.resonance.api.constants;

/**
 * Where Application wide Constants should be stored
 *
 * @author Drew
 */
// https://www.last.fm/api
public class Constants {
  public static final String ROOTURL = "http://ws.audioscrobbler.com/2.0/";
  public static final String ARTIST_GET_SIMILAR = "?method=artist.getsimilar&artist=";
  public static final String API_KEY = "&api_key=SNIP";
  public static final String JSON_FORMAT = "&format=json";
  // Artist plays
  // https://www.last.fm/api/show/artist.getInfo
  public static final String ARTIST_GET_INFO = "?method=artist.getinfo&artist=";
  // Track ID, plays, rating?, name, duration, artist, similar to
  // https://www.last.fm/api/show/track.getInfo
  public static final String TRACK_GET_INFO = "?method=track.getInfo";
  // https://www.last.fm/api/show/track.getSimilar
  public static final String TRACK_GET_SIMILAR = "?method=track.getsimilar&artist=";
  // User.getLovedTracks?
  public static final String USER_GET_LOVED_TRACKS = "?method=user.getlovedtracks&user=";

  // Parameter Map Keys
  public static final String ARTIST = "artist";
  // Database ID
  public static final String ARTIST_ID = "artist_id";
  public static final String TRACK = "track";
  // Database ID
  public static final String TRACK_ID = "track_id";
}
