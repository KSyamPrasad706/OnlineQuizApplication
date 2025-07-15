package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    // Use your provided details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quiz_app";  // your DB name
    private static final String USER = "root"; // your MySQL user
    private static final String PASSWORD = "password"; // your MySQL password

    private static Connection connection = null;
    // Private constructor to prevent instantiation
    private DBConnection() {}

    // Get connection (singleton)
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
               
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Connected to MySQL database successfully!");
            } catch (ClassNotFoundException e) {
                System.out.println(" MySQL JDBC Driver not found.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Close connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println(" Error closing database connection.");
            e.printStackTrace();
        }
    }
}
