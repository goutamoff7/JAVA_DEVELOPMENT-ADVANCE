package CRUDop;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import inJDBCUtil.JDBCUtilPreparedStatement;


public class InsertMySqlQueryPreparedStatement {

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
				String insertQuery = "insert into students(s_roll,s_name,city) values (?,?,?);";		
				preparedStatement = connection.prepareStatement(insertQuery);
				if(preparedStatement!=null)
				{
					System.out.print("Enter the Student's Roll No.: ");
					int s_roll = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter the Student's Name: ");
					String s_name = scan.nextLine();
					System.out.print("Enter city: ");
					String city = scan.nextLine();
					// here (?,?,?) of the query
					//1 = 1st ? = s_roll
					//2 = 2nd ? = s_name
					//3 = 3rd ?	= city					
					preparedStatement.setInt(1,s_roll);
					preparedStatement.setString(2,s_name);
					preparedStatement.setString(3,city);
					
					int rowEffected = preparedStatement.executeUpdate();
					if(rowEffected==1)
						System.out.println("Row Inserted");
					else
						System.out.println("Row Not Inserted");
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
