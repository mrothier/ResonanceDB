package connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class upload {
	static final String databasePrefix ="ResonanceDB";
	static final String netID ="jaron"; // Please enter your netId
	static final String hostName ="resonancedb.ctyc91zxkqhu.us-east-2.rds.amazonaws.com:1150";
	static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix;
	static final String password="Arj4EW2F"; // please enter your own password

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    public void Connection(){
  	  
	      try {
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("databaseURL"+ databaseURL);
	            connection = DriverManager.getConnection(databaseURL, netID, password);
	            System.out.println("Successfully connected to the database");
	         }
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } // end of Connection
    
    public void simpleQuery(String sqlQuery) {
	    
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate(sqlQuery);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    } // end of simpleQuery method
    
    private static Set<String> posible2(String posLoc) {
        Set<String> result = new TreeSet<String>();
        BufferedReader br = null;
        try {
           br = new BufferedReader(new FileReader(new File(posLoc)));
           String availalbe;
           while((availalbe = br.readLine()) != null) {
               result.add(availalbe);            
           }
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
           if (br != null) {
              try {
                 br.close();
              } catch (IOException e) {
                 e.printStackTrace();
              }
           }
        }
        return result;
    }
    
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		upload demoObj = new upload();
    	demoObj.Connection();
    	File file = new File("tags.dat");
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String line = br.readLine();
    	
        while((line = br.readLine()) != null) {            
        
    		String[] parts = line.split("\t");
    		String sqlQuery;
    		/*if(parts.length < 4) {
    			sqlQuery ="insert into artists(aID,aName,aPlays,picture) values (" + parts[0] +",\"" +parts[1] + "\",0,\"" + "No picture" + "\");";	
    		}
    		else {
    			sqlQuery ="insert into artists(aID,aName,aPlays,picture) values (" + parts[0] +",\"" +parts[1] + "\",0,\"" + parts[3] + "\");";	
    		}*/
    		
    		sqlQuery = "insert into tags(tID, tValue, taggings) values (" + parts[0] + ",\"" + parts[1] + "\",0);";
    		
    		
    		demoObj.simpleQuery(sqlQuery);
    		
    		}
        System.out.println("All Done!");
    	}	
    	
	}
