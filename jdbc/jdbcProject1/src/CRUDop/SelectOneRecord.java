package CRUDop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import inJDBCUtil.JDBCUtilPreparedStatement;

public class SelectOneRecord {

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
				String selectQuery = "select * from students where s_roll = ?";	
				preparedStatement = connection.prepareStatement(selectQuery);
				if(preparedStatement!=null)
				{
					System.out.print("Enter the Student Roll to search details: ");
					int s_roll = scan.nextInt();
					// here 1 = 1st ? 
					preparedStatement.setInt(1, s_roll);
					resultSet = preparedStatement.executeQuery();
					if(resultSet!=null)
					{
						if(resultSet.next())
						{
							System.out.println("|   s_roll   |  |   s_name   |  |   city   |  |   college   |");
							System.out.println();
							//1 = 1st column of the database = s_roll
							//2 = 2nd column of the database = s_name
							//3 = 3rd column of the database = s_city
							//4 = 4th column of the database = s_college
							System.out.println("     "+resultSet.getInt(1)+"            "+resultSet.getString(2)+"      "+resultSet.getString(3)+"       "+resultSet.getString(4));
						
						}
					}
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
			finally {
				scan.close();
			}
		}
		

	}

}
