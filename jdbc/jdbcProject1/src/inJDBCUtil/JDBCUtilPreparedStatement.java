package inJDBCUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtilPreparedStatement 
{
// task to get the properties from the application file and loading the driver with connection
	public static Connection getJdbcConnection() throws IOException, SQLException
	{
		FileInputStream fis = new FileInputStream("Application.properties");
		Properties p = new Properties();
		p.load(fis);
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String password = p.getProperty("password");
			
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
			
		Connection connection = DriverManager.getConnection(url,user,password);
		return connection;		
		
	}
	
	public static void closeResourse(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet) throws SQLException, IOException
	{
		if(connection!=null)
			connection.close();
		if(preparedStatement!=null)
			preparedStatement.close();
		if(resultSet!=null)
			resultSet.close();
	}
}
