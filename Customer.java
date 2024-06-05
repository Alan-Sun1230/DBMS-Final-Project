import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    private String server = "jdbc:mysql://140.119.19.73:3315/";
    private String database = "111306061"; // change to your own database
    private String url = server + database + "?useSSL=false";
    private String DBUsername = "111306061"; // change to your own user name
    private String DBPassword = "wrzvm"; // change to your own password

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
            int userID = Integer.parseInt(username);
            int passwords = Integer.parseInt(password);
            String query1 = "SELECT UserID FROM d_user WHERE UserID = " + userID;
            String query2 = "SELECT UserID FROM d_user WHERE Password = " + passwords + " AND UserID = " + userID;
            String query3 = "SELECT UserID FROM d_customer WHERE UserID = " + userID;

            try (Statement stat = conn.createStatement();
                 ResultSet result1 = stat.executeQuery(query1)) {

                if (result1.next()) {
                    try (ResultSet result2 = stat.executeQuery(query2)) {
                        if (result2.next()) {
                            try (ResultSet result3 = stat.executeQuery(query3)) {
                                if (result3.next()) {
                                    new FrameCustomer(conn,userID);
                                } else {
                                    throw new WrongDataError("CustomerID does not exist");
                                }
                            }
                        } else {
                            throw new WrongDataError("UserID does not exist or Password is wrong");
                        }
                    }
                } else {
                    throw new WrongDataError("UserID does not exist");
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
