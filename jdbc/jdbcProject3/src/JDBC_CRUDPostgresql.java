import java.sql.*;
import java.util.Scanner;

public class JDBC_CRUDPostgresql {
    static Connection connection = null;
    static Scanner scan = new Scanner(System.in);
    static PreparedStatement preparedStatement = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) {
        try {
            connection = JDBCProstgresqlUtil.getJdbcConnection();
            while (true) {
                System.out.println(
                        "Press 1 to Create Table " +
                                "\t Press 2 to Insert " +
                                "\t Press 3 to Read " +
                                "\n Press 4 to Update " +
                                "\t\t Press 5 to Delete" +
                                "\t Press 6 to Drop or Truncate Table" +
                                "\n Press 7 to Exit");

                System.out.print("Enter Your Choice : ");
                int choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        create();
                        break;
                    case 2:
                        insert();
                        break;
                    case 3:
                        read();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                        drop();
                        break;
                    case 7:
                        JDBCProstgresqlUtil.closeResourse(connection, preparedStatement, statement, resultSet);
                        return;
                    default:
                        System.out.println("Enter Correct Choice");
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCProstgresqlUtil.closeResourse(connection, preparedStatement, statement, resultSet);
        }
    }

    // Creating Table inside the database
    public static void create() throws SQLException {
        String sql = "create table student(s_id SERIAL primary key, s_name varchar(20), s_marks numeric);";
        try {
            statement = connection.createStatement();
            if (statement != null) {
                int rowsEffected = statement.executeUpdate(sql);
                if (rowsEffected == 0)
                    System.out.println("Table Created Successfully");
                else
                    throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Table Creation Failed");
        }
    }

    // Inserting Data into the Table
    public static void insert() {
        String sql = "insert into student(s_name,s_marks) values(?,?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                scan.nextLine(); // to flush
                System.out.print("Enter Student's Name :");
                String s_name = scan.nextLine();
                System.out.print("Enter Marks :");
                int s_marks = scan.nextInt();

                preparedStatement.setString(1, s_name);
                preparedStatement.setInt(2, s_marks);

                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0)
                    System.out.println("Record Insertion Successful");
                else
                    throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Record Insertion Failed");
        }
    }

    // Retrieve Data from the Table
    public static void read() throws SQLException {
        String sql = "select * from student";
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                resultSet = preparedStatement.executeQuery();
                System.out.println(resultSet.wasNull());
                if (!resultSet.next()) // check whether any data returned or not
                    System.out.println("No data found");
                else {
                    // s_roll is auto_incremented and college has default value
                    System.out.println(" s_roll    s_name    s_marks");
                    do {
//                    System.out.println("   " + resultSet.getInt(1) +
//                            "      " + resultSet.getString(2) +
//                            "    " + resultSet.getInt(3)
//                    );
                        // or use columnLabel
                        System.out.println("   " + resultSet.getInt("s_id") +
                                "     " + resultSet.getString("s_name") +
                                "    " + resultSet.getInt("s_marks")
                        );
                    }while (resultSet.next());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Updating Records in the Table
    public static void update() throws SQLException {
        String sql = "update student set s_name = ?, s_marks = ? where s_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                System.out.print("Enter Student's Id to update details : ");
                int s_id = scan.nextInt();
                scan.nextLine();
                System.out.print("Enter Student name : ");
                String s_name = scan.nextLine();
                System.out.print("Enter the marks : ");
                int s_marks = scan.nextInt();

                preparedStatement.setString(1, s_name);
                preparedStatement.setInt(2, s_marks);
                preparedStatement.setInt(3, s_id);

                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0)
                    System.out.println("Record Update Successful");
                else
                    throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Record Update Failed");
        }
    }

    // Deletion of Record from the Table
    public static void delete() throws SQLException {
        String sql = "delete from student where s_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                System.out.print("Enter Student's Id to Delete Record : ");
                int s_id = scan.nextInt();
                preparedStatement.setInt(1, s_id);
                int rowsEffected = preparedStatement.executeUpdate();
                if (rowsEffected > 0)
                    System.out.println("Record Deletion Successful");
                else
                    throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Record Deletion Failed");
        }

    }

    // Drop(delete all records with table structure) or Truncating(Delete all record only) the Table from Database
    public static void drop() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            if (statement != null) {
                System.out.println("Press 1 to Delete the Table\tPress 2 to Delete all the Records from the Table");
                System.out.print("Enter Your Choice :");
                int choice = scan.nextInt();
                String sql;
                if (choice == 1) {
                    sql = "Drop table student;";
                    int rowsEffected = statement.executeUpdate(sql);
                    if (rowsEffected == 0)
                        System.out.println("Table Dropped Successfully");
                    else
                        throw new SQLException();
                } else if (choice == 2) {
                    sql = "Truncate table student;";
                    int rowsEffected = statement.executeUpdate(sql);
                    if (rowsEffected == 0)
                        System.out.println("All Records Deleted Successfully");
                    else
                        throw new SQLException();
                } else
                    System.out.println("Enter Correct Choice");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
