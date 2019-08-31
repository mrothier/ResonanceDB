package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




public class Populate {
	static final String databasePrefix ="ResonanceDB";
	static final String netID ="jaron"; // Please enter your netId
	static final String hostName ="resonancedb.ctyc91zxkqhu.us-east-2.rds.amazonaws.com:1150";
	static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix;
	static final String password="Arj4EW2F"; // please enter your own password

    private Connection connection = null;
    private Statement statement = null;
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				Populate demoObj = new Populate();
		    	demoObj.Connection();
		    	demoObj.generateUsers();
				demoObj.cleanTags();
				demoObj.listDirtyTags();
		    	demoObj.generateUserTaggedArtists();
		    	demoObj.generateTracks();
		    	demoObj.generateCompose();
		    	demoObj.simulateLikes();
		    	
	}
	public void cleanTags() throws IOException {
		File file = new File("tags.dat");
		Scanner input = new Scanner(file);
		String line = input.nextLine();
		int tagsChecked = 0;
		int numDirtyTags = 0;
		boolean clean;
		String fregEx = "[\\w]*[fF]+[uU]+[cC]+[kK]+[\\w]*";
		String sregEx = "[\\w]*[sS]+[hH]+[iI]+[tT]+[\\w]*";
		String xregEx = "[sS]+[eE]+[xX]+";
		String dregEx = "[\\w]*[dD]+[aA]+[mM]+[nN]+[\\w]*";
		String bregEx = "[\\w]*[bB]+[iI]+[tT]+[cC]+[hH]+[\\w]*";
		String aregEx = "[aA]+[sS]+[sS]+";
		String hregEx = "[hH]+[eE]+[lL]+[lL]+";
		String bdregEx = "[bB]+[aA]+[sS]+[tT]+[aA]+[rR]+[dD]+";
		String wregEx = "[wW]+[hH]+[oO]+[rR]+[eE]+";
		FileWriter writer = new FileWriter("cleanTags.csv");
		
		while(tagsChecked < 11946) {
			line = input.nextLine();
			String parts[] = line.split("\t");
			String words[] = parts[1].split("\\s");
			clean = true;
			for(int i = 0; i < words.length; i++) {
				if(words[i].matches(fregEx)||words[i].matches(sregEx)||words[i].matches(xregEx)||words[i].matches(dregEx)||words[i].matches(bregEx)
						||words[i].matches(aregEx) ||words[i].matches(hregEx)||words[i].matches(bdregEx)||words[i].matches(wregEx)) {
					numDirtyTags++;
					clean = false;
					break;
				}
				
				
				
			}
			if(clean) {
					writer.append(parts[0]);
					writer.append(',');
					writer.append(parts[1]);
					writer.append('\n');
					String sqlQuery = "insert into tags(tID, tValue, taggings) values (" + parts[0] + ",\"" + parts[1] + "\",0);";
					simpleQuery(sqlQuery);
			}
			tagsChecked++;
		}
		System.out.println("Number of dirty tags = " + numDirtyTags);
		writer.close();
		input.close();
	
	}
	
	public void listDirtyTags() throws IOException {
		File file = new File("tags.dat");
		Scanner input = new Scanner(file);
		String line = input.nextLine();
		int tagsChecked = 0;
		int numDirtyTags = 0;
		
		String fregEx = "[\\w]*[fF]+[uU]+[cC]+[kK]+[\\w]*";
		String sregEx = "[\\w]*[sS]+[hH]+[iI]+[tT]+[\\w]*";
		String xregEx = "[sS]+[eE]+[xX]+";
		String dregEx = "[\\w]*[dD]+[aA]+[mM]+[nN]+[\\w]*";
		String bregEx = "[\\w]*[bB]+[iI]+[tT]+[cC]+[hH]+[\\w]*";
		String aregEx = "[aA]+[sS]+[sS]+";
		String hregEx = "[hH]+[eE]+[lL]+[lL]+";
		String bdregEx = "[bB]+[aA]+[sS]+[tT]+[aA]+[rR]+[dD]+";
		String wregEx = "[wW]+[hH]+[oO]+[rR]+[eE]+";
		FileWriter record = new FileWriter("dirty.csv");
		
		while(tagsChecked < 11946) {
			line = input.nextLine();
			String parts[] = line.split("\t");
			String words[] = parts[1].split("\\s");
			
			for(int i = 0; i < words.length; i++) {
				if(words[i].matches(fregEx)||words[i].matches(sregEx)||words[i].matches(xregEx)||words[i].matches(dregEx)||words[i].matches(bregEx)
						||words[i].matches(aregEx) ||words[i].matches(hregEx)||words[i].matches(bdregEx)||words[i].matches(wregEx)) {
					record.append(parts[0]);
					record.append(',');
					record.append(parts[1]);
					record.append('\n');
					numDirtyTags++;
					System.out.println("Added: " + parts[0] + "," + parts[1]);
					break;
				}
				
				
				
			}
			
			tagsChecked++;
		}
		System.out.println("Number of dirty tags = " + numDirtyTags);
		record.close();
		
		input.close();
	}
	
	public void generateUsers() throws FileNotFoundException {
		File file = new File("user_taggedartists.dat");
		Scanner input = new Scanner(file);
		String line = input.nextLine();
		line = input.nextLine();
		String user = "";
		String username;
		String password;
		
		while(line != null) {
			String[] parts = line.split("\t");
			if(!user.equals(parts[0])) {
				user = parts[0];
				username = "user" + user;
				password = "pass" + user;
				
				String sqlQuery = "insert into users(username, password, uID) values (\"" + username + "\",\"" + password + "\"," + user + ");";
				simpleQuery(sqlQuery);
			}
			
			
			line = input.nextLine();
		}
		
		input.close();
		System.out.println("All Done!");
	}
	
	public void generateUserTaggedArtists() throws FileNotFoundException {
		File file = new File("dirty.csv");
		Scanner input = new Scanner(file);
		String dirty[] = new String[211];
		int count = 0;
		String line;
		String user = "";
		String artist;
		String tag;
		
		while(input.hasNextLine()) {
			line = input.nextLine();
			String parts[] = line.split(",");
			dirty[count++] = parts[0];
			
		}
		input.close();
		
		file = new File("user_taggedartists.dat");
		input = new Scanner(file);
		line = input.nextLine();
		line = input.nextLine();
		while(line != null) {
			String[] parts = line.split("\t");
			user = parts[0];
			artist = parts[1];
			tag = parts[2];
			boolean clean = true;
				for(int i = 0; i < dirty.length; i++) {
					if(dirty[i].equals(tag)) {
						clean = false;
					}
				}
				if(clean) {
					String sqlQuery = "insert into uses(uID, aID, tID) values (" +user + "," + artist + "," + tag + ");";
					simpleQuery(sqlQuery);
				}
			
			
			line = input.nextLine();
		}
		
		input.close();
		System.out.println("All Done!");
	}
	
	public void generateTracks() throws IOException {
		File file = new File("Song Dataset.csv");
		BufferedReader input = new BufferedReader(new FileReader(file));
    	String line = input.readLine();
		int trID = 1;
		String trName = "";
		
		
		while((line = input.readLine()) != null) {
			String[] parts = line.split(",");
			
				
				trName = parts[0];
				
				String sqlQuery = "insert into tracks(trID, trName, trPlays, duration, rating) values (" + trID + ",\"" + trName + "\",0,0,0);";
				simpleQuery(sqlQuery);
				trID++;
		}
		
		input.close();
		System.out.println("All Done!");
		
	}
	
	
	public void generateCompose() throws IOException {
		File file = new File("Song Dataset.csv");
		BufferedReader input = new BufferedReader(new FileReader(file));
    	String line = input.readLine();
		String trName = "";
		String aName = "";
		
		
		while((line = input.readLine()) != null) {
			String[] parts = line.split(",");
			
				
				trName = parts[0];
				aName = parts[1];
				String sqlQuery = "call composeTrack(\"" + trName + "\",\"" + aName + "\");";
				simpleQuery(sqlQuery);
				
		}
		
		input.close();
		System.out.println("All Done!");
		
	}
	
	
	public void simulateLikes() {
		
		
			for(int i = 2; i < 2100; i++) {
				
				String sqlQuery = "call simulateUserLikes(" + i + ");";
				simpleQuery(sqlQuery);
				
			}
			
			
				
				
		
		
		
		System.out.println("All Done!");
		
	}
	
	
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
}
