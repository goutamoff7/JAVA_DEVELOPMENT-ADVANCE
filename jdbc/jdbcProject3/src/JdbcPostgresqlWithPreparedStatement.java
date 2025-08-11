import java.sql.*;
import java.util.Scanner;

public class JdbcPostgresqlWithPreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/college";
        String username = "postgres";
        String password = "Goutam@12345";
        Connection connection;
        String sql = "SELECT * FROM student where s_id = ?";
        // Assuming that the college db has a student table with fields : s_id,s_name,s_marks
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the s_id : ");
        int sId = scanner.nextInt();
        {
            try {
                connection = DriverManager.getConnection(url,username,password); // Create Connection
                System.out.println("Connection Established");
                PreparedStatement preparedStatement = connection.prepareStatement(sql); // Caching Query
                preparedStatement.setInt(1,sId); // Create Statement
                ResultSet resultSet = preparedStatement.executeQuery(); // Execute Statement
                if(!resultSet.next()) // check whether any data returned or not
                    System.out.println("No data found for the given s_id");
                else {
                     do{
                        System.out.print(resultSet.getInt("s_id") + " : ");
                        System.out.print(resultSet.getString("s_name") + " : ");
                        System.out.println(resultSet.getInt("s_marks"));
                    }while (resultSet.next());
                }
                connection.close(); // Close Connection
                System.out.println("Connection Closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }

}
