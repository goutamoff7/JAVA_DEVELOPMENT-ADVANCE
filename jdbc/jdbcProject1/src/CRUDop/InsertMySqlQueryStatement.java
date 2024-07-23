package CRUDop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import inJDBCUtil.JDBCUtilStatement;



public class InsertMySqlQueryStatement {

	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statement = null;
		try 
		{
			connection = JDBCUtilStatement.getJdbcConnection();
			if(connection!=null)
			{
				statement = connection.createStatement();
				if(statement!=null)
				{
					/* in mydb database, students is a table with colums( s_roll int primary key,s_name varchar(50),city varchar(20),college varchar(50) default "SANAKA")*/
					String insertQuery = "insert into students(s_roll,s_name,city) values (1,'Arpan Kundu','Durgapur'),(2,'Goutam Dam','Durgapur'),(3,'Bapi Mondal','Bishnupur');";
					int rowEffected = statement.executeUpdate(insertQuery);
					if(rowEffected==1)
						System.out.println("Row Inserted");
					else
						System.out.println("Row not Inserted");
				}
									
			}
				
		} 
		catch (IOException | SQLException e) 
		{
			
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				JDBCUtilStatement.closeResourse(connection, statement);
			} catch (SQLException | IOException e) {
				System.out.println(e.getMessage());
			}
		}
		

	}

}
