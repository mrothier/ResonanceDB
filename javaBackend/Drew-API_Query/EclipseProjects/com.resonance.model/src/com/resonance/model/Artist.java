package com.resonance.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

/**
 * The Model for a Last.FM Musical Artist
 *
 * @author Drew
 */
public class Artist implements Serializable, Comparable<Artist> {

  private static final long serialVersionUID = 8449957858171259085L;
  private long ID;

  // Fields from JSON
  String name;
  String mbid;
  String url;
  List<Image> image;
  int streamable;
  int ontour;
  Stats stats;
  Similar similar;
  TagHolder tags;

  // Constructors
  public Artist() {}

  public Artist(long iD) {
    this.ID = iD;
  }

  public Artist(
      String name,
      String mbid,
      String url,
      List<Image> image,
      int streamable,
      int ontour,
      Stats stats,
      Similar similar,
      TagHolder tags) {
    this.name = name;
    this.mbid = mbid;
    this.url = url;
    this.image = image;
    this.streamable = streamable;
    this.ontour = ontour;
    this.stats = stats;
    this.similar = similar;
    this.tags = tags;
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

  public void setName(String name) {
    this.name = name;
  }

  public String getMbid() {
    return mbid;
  }

  public void setMbid(String mbid) {
    this.mbid = mbid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<Image> getImage() {
    return image;
  }

  public void setImage(List<Image> image) {
    this.image = image;
  }

  public int getStreamable() {
    return streamable;
  }

  public void setStreamable(int streamable) {
    this.streamable = streamable;
  }

  public int getOntour() {
    return ontour;
  }

  public void setOntour(int ontour) {
    this.ontour = ontour;
  }

  public Stats getStats() {
    return stats;
  }

  public void setStats(Stats stats) {
    this.stats = stats;
  }

  public Similar getSimilar() {
    return similar;
  }

  public void setSimilar(Similar similar) {
    this.similar = similar;
  }

  public TagHolder getTags() {
    return tags;
  }

  public void setTags(TagHolder tags) {
    this.tags = tags;
  }

  // Inner Classes
  public static class Image {

    @Json(name = "#text")
    @SerializedName("#text")
    private String text;

    private String size;

    public String getText() {
      return text;
    }

    public String getSize() {
      return size;
    }

    @Override
    public String toString() {
      return "Image [text=" + text + ", size=" + size + "]";
    }
  }

  public static class Stats {

    private int listeners;
    private int playcount;

    public int getListeners() {
      return listeners;
    }

    public int getPlaycount() {
      return playcount;
    }

    @Override
    public String toString() {
      return "Stats [listeners=" + listeners + ", playcount=" + playcount + "]";
    }
  }

  public static class Similar {

    List<innerArtist> artist;

    @Override
    public String toString() {
      return "Similar [artist=" + artist + "]";
    }
  }

  public static class innerArtist {

    String name;
    String url;
    List<Image> image;

    @Override
    public String toString() {
      return "innerArtist [name=" + name + ", url=" + url + ", image=" + image + "]";
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (ID ^ (ID >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Artist other = (Artist) obj;
    if (ID != other.ID) return false;
    return true;
  }

  @Override
  public int compareTo(Artist artist) {
    return Long.compare(this.getID(), artist.getID());
  }

  @Override
  public String toString() {
    return "Artist [ID="
        + ID
        + ", name="
        + name
        + ", mbid="
        + mbid
        + ", url="
        + url
        + ", image="
        + image
        + ", streamable="
        + streamable
        + ", ontour="
        + ontour
        + ", stats="
        + stats
        + ", similar="
        + similar
        + ", tags="
        + tags
        + "]";
  }
}
