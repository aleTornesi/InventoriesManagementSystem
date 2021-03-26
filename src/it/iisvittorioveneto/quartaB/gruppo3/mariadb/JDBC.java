package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import java.sql.*;

public class JDBC {

    static final String url = "jdbc:mariadb://192.168.64.3:3306/InventoriesManagementDB";


    public static String logIn(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "username", null);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Users where email = '" + email + "' and password='" + password + "'");
        if (rs.next()) {
            return rs.getString("username");
        }
        rs.close();
        statement.close();
        connection.close();
        return null;
    }


}
