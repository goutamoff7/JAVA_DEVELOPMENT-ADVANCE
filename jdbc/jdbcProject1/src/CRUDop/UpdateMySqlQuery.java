package CRUDop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import inJDBCUtil.JDBCUtilPreparedStatement;

public class UpdateMySqlQuery {

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
				String insertQuery = "update students set city = ? where s_roll = ?;";		
				preparedStatement = connection.prepareStatement(insertQuery);
				if(preparedStatement!=null)
				{
					System.out.print("Enter the Student's Roll No. to update city: ");
					int s_roll = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter Updated city: ");
					String city = scan.nextLine();
					// here (?,?) of the query
					//1 = 1st ? = city
					//2 = 2nd ? = s_roll						
					preparedStatement.setString(1,city);
					preparedStatement.setInt(2,s_roll);
					
					int rowEffected = preparedStatement.executeUpdate();
					if(rowEffected==1)
						System.out.println("Row Updated");
					else
						System.out.println("Row Not Updated");
				}
				
			}
				
		} 
		catch (IOException | SQLException e) 
		{
			
			e.printStackTrace();
		}
		finally
		{
			try {
				JDBCUtilPreparedStatement.closeResourse(connection, preparedStatement,resultSet);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
