package com.resonance.model;

import java.io.Serializable;

/**
 * The Model for a Last.FM Track which are similar to other tracks
 *
 * @author Drew
 */
public class SimilarTrack implements Serializable, Comparable<SimilarTrack> {

  private static final long serialVersionUID = 3565472958917674276L;

  private long ID;

  private String name;
  private String playcount;
  private Artist artist;

  public SimilarTrack() {}

  public SimilarTrack(String name, String playcount, Artist artist) {
    this.name = name;
    this.playcount = playcount;
    this.artist = artist;
  }
  
  public static class Artist{
    String name;
  }
  
  public long getID() {
  return ID;}

  public void setID(long iD) {
  ID = iD;}

  public String getName() {
  return name;}

  public void setName(String name) {
  this.name = name;}

  public String getPlaycount() {
  return playcount;}

  public void setPlaycount(String playcount) {
  this.playcount = playcount;}

  public Artist getArtist() {
  return artist;}

  public void setArtist(Artist artist) {
  this.artist = artist;}

  @Override
  public int compareTo(SimilarTrack o) {
    return Long.compare(this.getID(), o.getID());
  }
}
