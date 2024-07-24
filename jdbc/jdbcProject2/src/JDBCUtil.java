import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


public class JDBCUtil 
{
	
	public static Connection getJdbcConnection()
	{
	// Import Packages (here my-sql-connector)
    // Load Driver
    // Register the Driver
    // Establish the connection
    // Execute the SQL Queries
		Connection connection = null;	
    
    try {
        	FileInputStream fis = new FileInputStream("Application.properties");
        	Properties p = new Properties();
        	p.load(fis);
        	String url = p.getProperty("url");
        	String username = p.getProperty("username");
        	String password = p.getProperty("password");
        
        	try 
        	{
        		// Establish the connection
        		connection =  DriverManager.getConnection(url,username,password);
        		if(connection!=null)
        		{
        			System.out.println(url);
        			System.out.println(username);
        			System.out.println(password);
        		}
        	}
        	catch(SQLException e)
        	{
        		System.out.println(e.getMessage());
        	}
    	}
    	catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
	return connection;
	}
	
	public static void closeResourse(Connection connection, PreparedStatement preparedStatement,Statement statement,ResultSet resultSet,Scanner scan)
	{
		try 
		{
			if(connection!=null)
				connection.close();
			if(preparedStatement!=null)
				preparedStatement.close();
			if(statement!=null)
				statement.close();
			if(resultSet!=null)
				resultSet.close();
			if(scan!=null)
				scan.close();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
  }
	
