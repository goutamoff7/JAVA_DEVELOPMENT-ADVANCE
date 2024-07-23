package CRUDop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import inJDBCUtil.JDBCUtilStatement;


public class CreateMySqlQueryStatement {

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
					String createQuery = "create table students ( s_roll int primary key,s_name varchar(50),city varchar(20),college varchar(50) default \"SANAKA\");";
					
					String dropQuery = "drop table students";
					
					System.out.print("Press 1 to Create Table \t Press 2 to Drop Table\nEnter Your Choice: ");
					Scanner scan = new Scanner(System.in);
					int choice = scan.nextInt();
					int rowEffected;
					
					switch(choice){
					case 1:  rowEffected = statement.executeUpdate(createQuery);
							 if(rowEffected==0)
								 System.out.println("Table Created");
							 else
								 System.out.println("Table not Created");
							 break;
					case 2:  rowEffected = statement.executeUpdate(dropQuery);
					 		 if(rowEffected==0)
					 			 System.out.println("Table Dropped");
					 		 else
					 			 System.out.println("Table not Dropped");
					 		 break;
					default : System.out.println("Wrong Choice");
					
					}
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
