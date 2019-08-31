package com.resonance.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * The Model for a Last.FM Track
 *
 * @author Drew
 */
public class Track implements Serializable, Comparable<Track> {

  private static final long serialVersionUID = 7836875638136497394L;
  private long ID;

  private String name;
  private String mbid;
  private String url;
  private long duration;
  private Streamable streamable;
  private long listeners;
  private long playcount;
  private Artist artist;
  private Album album;
  private TagHolder toptags;

  // Constructors
  public Track() {}

  public Track(
      String name,
      String mbid,
      String url,
      long duration,
      Streamable streamable,
      long listeners,
      long playcount,
      Artist artist,
      Album album,
      TagHolder toptags) {
    super();
    this.name = name;
    this.mbid = mbid;
    this.url = url;
    this.duration = duration;
    this.streamable = streamable;
    this.listeners = listeners;
    this.playcount = playcount;
    this.artist = artist;
    this.album = album;
    this.toptags = toptags;
  }

  // Getters/Setters
  public long getID() {
    return ID;
  }

  public void setID(long iD) {
    ID = iD;
  }

  public String getName() {
    return name;
  }

  public String getMbid() {
    return mbid;
  }

  public String getUrl() {
    return url;
  }

  public long getDuration() {
    return duration;
  }

  public Streamable getStreamable() {
    return streamable;
  }

  public long getListeners() {
    return listeners;
  }

  public long getPlaycount() {
    return playcount;
  }

  public Artist getArtist() {
    return artist;
  }

  public Album getAlbum() {
    return album;
  }

  public TagHolder getToptags() {
    return toptags;
  }

  public static class Streamable {
    @SerializedName("#text")
    private int text;

    private int fulltrack;

    public int getText() {
      return text;
    }

    public int getFulltrack() {
      return fulltrack;
    }

    @Override
    public String toString() {
      return "Streamable [text=" + text + ", fulltrack=" + fulltrack + "]";
    }
  }

  public static class Album {
    private String artist;
    private String title;
    private String mbid;
    private String url;
    // JSON also has images - ignoring

    public String getArtist() {
      return artist;
    }

    public String getTitle() {
      return title;
    }

    public String getMbid() {
      return mbid;
    }

    public String getUrl() {
      return url;
    }

    @Override
    public String toString() {
      return "Album [artist="
          + artist
          + ", title="
          + title
          + ", mbid="
          + mbid
          + ", url="
          + url
          + "]";
    }
  }

  public static class TagHolder {
    List<Tag> tag;

    @Override
    public String toString() {
      return "[tag=" + tag + "]";
    }
  }

  public static class Tag {
    String name;
    String url;

    public String getName() {
      return name;
    }

    public String getUrl() {
      return url;
    }

    @Override
    public String toString() {
      return "Tag [name=" + name + ", url=" + url + "]";
    }
  }

  public static class Artist {
    String name;
    String mbid;
    String url;
  }

  @Override
  public String toString() {
    return "Track [ID="
        + ID
        + ", name="
        + name
        + ", mbid="
        + mbid
        + ", url="
        + url
        + ", duration="
        + duration
        + ", streamable="
        + streamable
        + ", listeners="
        + listeners
        + ", playcount="
        + playcount
        + ", artist="
        + artist
        + ", album="
        + album
        + ", toptags="
        + toptags
        + "]";
  }

  @Override
  public int compareTo(Track track) {
    return Long.compare(this.getID(), track.getID());
  }
}
