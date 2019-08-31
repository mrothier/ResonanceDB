package com.resonance.db.sql;

import com.resonance.model.SQLResult;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLClient {

  static final String databasePrefix = "ResonanceDB";
  static final String netID = "SNIP";
  static final String hostName = "resonancedb.SNIP.us-east-2.rds.amazonaws.com:1150";
  static final String databaseURL = "jdbc:mysql://" + hostName + "/" + databasePrefix;
  static final String password = "SNIP";
  private static Connection connection = null;
  private static Statement statement = null;
  private static ResultSet resultSet = null;

  public boolean testConnection() {
    try {
      // Connect to Database
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(databaseURL, netID, password);
      statement = connection.createStatement();
      System.out.println("Successfully connected to database: " + databaseURL);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      // Close connections
      try {
        statement.close();
        connection.close();
        System.out.println("Query Connection Closed");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public SQLResult performQuery(String query) {
    try {
      // Connect to Database
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(databaseURL, netID, password);
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);
      return new SQLResult(connection, statement, resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean performUpdate(List<String> updateList) {
    try {
      // Connect to Database
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(databaseURL, netID, password);
      statement = connection.createStatement();
      for (String update : updateList) {
        try {
          int i = 0;
          while (statement.executeUpdate(update) == 0) {
            // Sometimes that update just doesn't want to go through
			// Lets do it 10 more times
			statement.executeUpdate(update);
            i++;
            if(i>=10) {
              break;
            }
          }
        } catch (Exception e) {
          System.out.println("Encountered an error but continuing update");
          e.printStackTrace();
        }
      }
      System.out.println("Update Complete");
      return true;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }
}
