package com.resonance.main;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.resonance.api.constants.Constants;
import com.resonance.db.sql.SQLClient;
import com.resonance.model.Artist;
import com.resonance.model.SQLResult;

/**
 * A Very quick and dirty program for grabbing all artists from the database, using that list of
 * artists to grab their corresponding playcount from the API, and push that playcount back into the
 * database
 *
 * @author dbeaudry
 */
public class Main {
  public static void main(String[] args) {
    /** Parameters to pass our HTTP Request Builder */
    Map<String, String> parameters = new HashMap<>();
    /** List of Artist Names from the DB */
    List<String> artistNameList = new ArrayList<>();
    ArtistCreator artistCreator = new ArtistCreator();
    /** List of Artists */
    List<Artist> artistList = new ArrayList<>();
    /** List of update statements to issue to the database */
    List<String> updateList = new ArrayList<>();
    /** Object which handles SQL queries and updates */
    SQLClient sqlClient = new SQLClient();

    try {
      // Define Query
      String query = "SELECT aName from artists WHERE aPlays = 0;";
      // Execute Query & Store result
      SQLResult sqlResult = sqlClient.performQuery(query);
      ResultSetMetaData metaData;
      metaData = sqlResult.getResultSet().getMetaData();
      int columns = metaData.getColumnCount();

      // Extract list of artist names from database query response
      while (sqlResult.getResultSet().next()) {
        for (int i = 1; i <= columns; i++) {
          artistNameList.add(sqlResult.getResultSet().getString(i));
        }
      }

      // Database query complete
      int size = artistNameList.size();
      System.out.println("Query Complete. ArtistList length = " + size);
      // Close database connection
      sqlResult.closeConnection();

      // Not worried about individual artist IDs/sorting in this particular application
      parameters.put(Constants.ARTIST_ID, "1");
      // Counter to keep track of progress
      int i = 0;

      // For each artist name, pass this to the API and ask for artist data
      for (String artistName : artistNameList) {
        i++;

        // Some name cleaning
        artistName = artistName.replaceAll("[\\s]&[\\s]", "+&+").trim();
        artistName = artistName.replaceAll("\t", " ");
        parameters.put(Constants.ARTIST, artistName);
        
        // Builds a request with the parameters, sends the request, 
        // and Creates an artist object with the API's response
        Artist artist = artistCreator.createArtist(parameters);
        if (artist != null) {
          artistList.add(artist);
          System.out.print("Created Artist: " + artistName);
        } else {
          System.out.print("Failed to create Artist: " + artistName);
        }
        System.out.println("\t\t\tProgress:" + i + "/" + size);
      }

      System.out.println("Artist creation complete. List Size: " + artistList.size());

      //Create updates to issue to the database
      for (Artist artist : artistList) {
        //Extract playcount
        int playCount = artist.getStats().getPlaycount();
        if (playCount > 0) {
          //Clean artist names a bit
          String artistName = artist.getName();
          artistName = artistName.replaceAll("'", "''").trim();
          artistName = artistName.replaceAll("\t", " ");
          //Create SQL Update
          String update =
              "UPDATE artists SET aPlays = " + playCount + " WHERE aName = '" + artistName + "';";
          System.out.println(update);

          //Store update in list
          updateList.add(update);
        }
      }

      //Issue updates to database
      System.out.println("PUSHING UPDATE TO DATABASE");
      sqlClient.performUpdate(updateList);
      System.out.println("UPDATE SUCCESSFUL");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
