package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import java.sql.*;

public class JDBC {

    static final String url = "jdbc:mysql://sql11.freesqldatabase.com/sql11402217";


    public static String logIn(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "sql11402217", "ga3LaxewmN");
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
