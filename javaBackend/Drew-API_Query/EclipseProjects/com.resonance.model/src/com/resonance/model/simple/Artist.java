package com.resonance.model.simple;

import java.io.Serializable;
import java.util.List;

/**
 * The Model for a Last.FM Musical Artist
 *
 * @author Drew
 */
public class Artist implements Serializable, Comparable<Artist> {

  private static final long serialVersionUID = 8449957858171259085L;
  private transient long ID;

  // Fields from JSON
  String name;
  //String mbid;
  //String url;
  //List<Image> image;
  //int streamable;
  //int ontour;
  //Stats stats;
  Similar similar;
  //TagHolder tags;

  // Constructors
  public Artist() {}

  public Artist(long iD) {
    this.ID = iD;
  }

  public Artist(String name, Similar similar) {
    super();
    this.name = name;
    this.similar = similar;
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

  public Similar getSimilar() {
    return similar;
  }

  public void setSimilar(Similar similar) {
    this.similar = similar;
  }

  // Inner Classes
  public static class Similar {

    List<innerArtist> artist;

    @Override
    public String toString() {
      return "Similar [artist=" + artist + "]";
    }
  }

  public static class innerArtist {

    String name;
    //String url;
    //List<Image> image;

    @Override
    public String toString() {
      return "innerArtist [name=" + name + "]";
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
    return "Artist [ID=" + ID + ", name=" + name + ", similar=" + similar + "]";
  }
}
