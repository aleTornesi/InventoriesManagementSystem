package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBC {

    static final String url = "jdbc:mariadb://localhost:3306/InventoriesManagementDB";

    private static final Credentials credentials = getCredentials();

    public static User logIn(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Users where email = '" + email + "' and password=SHA1('" + password + "');");
        if (rs.next()) {
            User user = new User(rs.getString("email"), rs.getString("password"), rs.getString("username"));
            rs.close();
            statement.close();
            connection.close();
            return user;
        }
        rs.close();
        statement.close();
        connection.close();
        return null;
    }

    public static void signUp(String email, String password, String username) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        statement.executeQuery("insert into Users values('" + email + "', SHA1('" + password + "'), '" + username + "');");
        statement.close();
        connection.close();
    }

    public static void insertInventory(Inventory inventory) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        statement.executeQuery("insert into Inventories (name, fk_UsersEmail) values('" + inventory.getName() + "', '" + inventory.getOwner().getEmail() + "');");
        statement.close();
        connection.close();
    }

    public static List<Inventory> getInventories(User owner, String inventoryName) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Inventories where name like '%" + inventoryName + "%' and" +
                " fk_UsersEmail='" + owner.getEmail() + " '");
        List<Inventory> inventories = new LinkedList<>();
        if (rs.next()) {
            inventories.add(new Inventory(owner, rs.getString("name"), rs.getFloat("full"), new LinkedList<>(), new LinkedList<>()));
            rs.close();
            statement.close();
            connection.close();
            return inventories;
        }
        System.out.println(inventories);
        rs.close();
        statement.close();
        connection.close();
        return null;
    }

    private static Credentials getCredentials() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = factory.newSAXParser();

            XMLParser handler = new XMLParser();
            saxParser.parse("DBCredentials.xml", handler);
            return handler.getCredentials();
        } catch (ParserConfigurationException | SAXException | IOException e) {return new Credentials();}
    }
}
