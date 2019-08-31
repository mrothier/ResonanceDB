package com.resonance.marshalling;

import java.io.IOException;

import com.resonance.model.Artist;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/*
 * Replaced by GSON
 */
@Deprecated
public class MoshiArtistParser {
	private final Moshi moshi = new Moshi.Builder().build();
	private final JsonAdapter<ArtistWrapper> jsonAdapter = moshi.adapter(ArtistWrapper.class);

	public MoshiArtistParser() {}

	/**
	 * Parse an Artist object from an HTTP Response in JSON Format
	 * @param response
	 * @return Artist (May return <code>null</code>)
	 */
	public Artist parseJSON(String response, long id) {
		try {
			ArtistWrapper artistWrapper = jsonAdapter.fromJson(response);
			Artist artist = artistWrapper.artist;
			artist.setID(id);
			return artist;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static class ArtistWrapper{
		Artist artist;
	}
}
