import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    private String server = "jdbc:mysql://140.119.19.73:3315/";
    private String database = "111306037"; // change to your own database
    private String url = server + database + "?useSSL=false";
    private String DBUsername = "111306037"; // change to your own user name
    private String DBPassword = "58g95"; // change to your own password

    private static Customer instance;

    private Customer() {}

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }

    public void login(String username, String password) throws WrongDataError {
        try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
            
            int passwords = Integer.parseInt(password);
            String query1 = "SELECT Name FROM User WHERE Name = " + username;
            String query2 = "SELECT Name FROM User WHERE Password = " + passwords + " AND UserID = " + username;
            String query3 = "SELECT Name FROM Customer WHERE UserID = " + username;

            try (Statement stat = conn.createStatement();
                 ResultSet result1 = stat.executeQuery(query1)) {

                if (result1.next()) {
                    try (ResultSet result2 = stat.executeQuery(query2)) {
                        if (result2.next()) {
                            try (ResultSet result3 = stat.executeQuery(query3)) {
                                if (result3.next()) {
                                    new FrameCustomer(conn,username);
                                } else {
                                    throw new WrongDataError("CustomerName does not exist");
                                }
                            }
                        } else {
                            throw new WrongDataError("UserName does not exist or Password is wrong");
                        }
                    }
                } else {
                    throw new WrongDataError("UserName does not exist");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class WrongDataError extends Exception {
        public WrongDataError(String Error) {
            super(Error);
        }
    }
}
