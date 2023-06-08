package com.wonkglorg.utilitylib.database;

import com.wonkglorg.utilitylib.logger.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IMPORTANT! Please add the mysql Jconnector to the project if you want to use MySql, I did not include this myself to not inflate the libraries
 * size. groupId : mysql artifactId : mysql-connector-java
 */
@SuppressWarnings("unused")
public class MySqlDatabase extends Database{
	
	protected final String USERNAME;
	protected final String URL;
	protected final String PASSWORD;
	
	public MySqlDatabase(String url, String username, String password, String databaseName) {
		super(databaseName, DatabaseType.MYSQL);
		
		if(username == null || url == null || password == null || DATABASE_NAME == null){
			throw new RuntimeException();
		}
		USERNAME = username;
		URL = url;
		PASSWORD = password;
		
	}
	
	@Override
	public void connect() {
		if(connection != null){
			return;
		}
		try{
			Class.forName(databaseType.getClassLoader());
			connection = DriverManager.getConnection(getDatabaseType().getDriver() + "//" + URL + "/" + DATABASE_NAME, USERNAME, PASSWORD);
		} catch(SQLException e){
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		} catch(ClassNotFoundException e){
			Logger.logFatal("Mysql-Connector-J Could not be found");
			Logger.logFatal("This is not an error for the plugin developer and should not be reported back to the creator of the library");
			Logger.logFatal("I did not include this dependency myself to not inflate the libraries size.");
			Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}