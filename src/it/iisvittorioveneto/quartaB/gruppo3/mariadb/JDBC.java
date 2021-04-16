package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import java.sql.*;

public class JDBC {

    static final String url = "jdbc:mariadb://192.168.64.3/InventoriesManagementDB";


    public static String logIn(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "username", "");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Users where email = '" + email + "' and password=SHA1('" + password + ")'");
        if (rs.next()) {
            rs.close();
            statement.close();
            connection.close();
            return email;
        }
        rs.close();
        statement.close();
        connection.close();
        return null;
    }

    public static void signUp(String email, String password, String username) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "username", "");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("insert into Users values('" + email + "', SHA1('" + password + "'), '" + username + "');");
        rs.close();
        statement.close();
        connection.close();
    }


}
