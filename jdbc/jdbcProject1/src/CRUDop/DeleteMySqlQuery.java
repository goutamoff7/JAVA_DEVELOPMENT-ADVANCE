package CRUDop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import inJDBCUtil.JDBCUtilPreparedStatement;


public class DeleteMySqlQuery {

	public static void main(String[] args) 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scan = new Scanner(System.in);
		try 
		{
			connection = JDBCUtilPreparedStatement.getJdbcConnection();
			if(connection!=null)
			{
				// in mydb database, students is a table with colums( s_roll int primary key,s_name varchar(50),city varchar(20),college varchar(50) default "SANAKA")
				String insertQuery = "delete from students where s_roll = ?;";		
				preparedStatement = connection.prepareStatement(insertQuery);
				if(preparedStatement!=null)
				{
					System.out.print("Enter the Student's Roll No. to delete the record : ");
					int s_roll = scan.nextInt();
					// here (?) of the query
					//1 = 1st ? = s_roll						
					preparedStatement.setInt(1,s_roll);
					
					int rowEffected = preparedStatement.executeUpdate();
					if(rowEffected==1)
						System.out.println("Row Deleted");
					else
						System.out.println("Row Not Deleted");
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
				JDBCUtilPreparedStatement.closeResourse(connection, preparedStatement,resultSet);
			} catch (SQLException | IOException e) {
				System.out.println(e.getMessage());
			}
		}
		

	}

}
