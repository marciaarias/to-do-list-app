package to_do_list_app;

import java.util.Properties;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataModule {
	
	Properties properties;
	FileInputStream reader;
	int id;

	//Create connection between the application and database.
	
	public Connection getConnection() throws Exception{
		
		try{			
			reader = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			
			properties = new Properties();
			properties.load(reader);
		  
			Connection connection = DriverManager.getConnection(
						  				properties.getProperty("url"),
						  				properties.getProperty("username"),
						  				properties.getProperty("password")
									);

			return connection;
		  
		}catch(Exception e){
				e.printStackTrace();
		}
	  
		return null;
	  
	}

}