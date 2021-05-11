package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.*;
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
        while (rs.next()) {
            inventories.add(new Inventory(rs.getInt("idInventory"), owner, rs.getString("name"), rs.getFloat("full"), new LinkedList<>(), new LinkedList<>()));
        }
        System.out.println(inventories);
        rs.close();
        statement.close();
        connection.close();
        return inventories;
    }


    public static List<Product> getInventoryProducts(int inventoryId, String name) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Products where Inventories_idInventory=" + inventoryId + ";");
        List<Product> products = new LinkedList<>();
        while (rs.next()) {
            products.add(new Product(rs.getInt("idProduct"), rs.getString("productName"), rs.getString("description"),
                    rs.getString("productType"), new Company(rs.getInt("Companies_idCompanies")),
                    new Inventory(rs.getInt("Inventories_idInventory")), rs.getInt("quantity")));
        }
        rs.close();
        statement.close();
        connection.close();
        return products;
    }

    private static Product getProduct(Integer idProduct) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Products where idProduct=" + idProduct + ";");
        Product product = null;
        while (rs.next()) {
            product = new Product(rs.getInt("idProduct"), rs.getString("productName"), rs.getString("description"), rs.getString("productType"), new Company(rs.getInt("Companies_idCompanies")));
        }
        rs.close();
        statement.close();
        connection.close();
        return product;
    }


    public static void deleteInventory(int idInventory) throws SQLException {
        deleteInventoryProducts(idInventory);
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Inventories where idInventory=" + idInventory + ";");
        rs.close();
        statement.close();
        connection.close();
    }

    private static void deleteInventoryProducts(int idInventory) throws SQLException {
        deleteInventoryProductsTags(idInventory);
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products where Inventory_idInventory=" + idInventory + ";");
        rs.close();
        statement.close();
        connection.close();
    }

    private static void deleteInventoryProductsTags(int idInventory) throws SQLException {
        List<Product> products = getInventoryProducts(idInventory, "");
        for (Product p: products) {
            deleteProductTags(p.getIdProduct());
        }
    }

    public static void deleteProductTags(int idProduct) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products_Tags where Products_idProducts=" + idProduct + ";");
        rs.close();
        statement.close();
        connection.close();
    }

    public static void deleteProduct(int idProduct) throws SQLException {
        deleteProductTags(idProduct);
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products where idProduct=" + idProduct + ";");
        rs.close();
        statement.close();
        connection.close();
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
