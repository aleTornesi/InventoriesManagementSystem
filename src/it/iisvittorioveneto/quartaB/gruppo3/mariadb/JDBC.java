package it.iisvittorioveneto.quartaB.gruppo3.mariadb;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.*;
import java.util.*;

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
        statement.executeQuery("insert into Inventories (name, fk_UsersEmail, full) values('" + inventory.getName() + "', '" + inventory.getOwner().getEmail() + "', " + inventory.getFull() + ");");
        statement.close();
        connection.close();
    }

    public static List<Inventory> getInventories(User owner, String inventoryName) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Inventories left join Inventories_Products on Inventories_Products.Inventories_idInventory = Inventories.idInventory where name like '%" + inventoryName + "%' and" +
                " fk_UsersEmail='" + owner.getEmail() + " '");
        List<Inventory> inventories = new LinkedList<>();

        while (rs.next()) {
            boolean wasAlreadyInstantiated = false;
            for (Inventory i: inventories) {
                if (i.getIdInventory() == rs.getInt("idInventory")) {
                    i.addProduct(new InventoryProduct(i, new Product(rs.getString("Products_name")), rs.getInt("quantity")));
                    wasAlreadyInstantiated = true;
                    break;
                }
            }
            if (!wasAlreadyInstantiated)
                inventories.add(new Inventory(rs.getInt("idInventory"), owner, rs.getString("name"), rs.getFloat("full"), new LinkedList<>()));
        }
        rs.close();
        statement.close();
        connection.close();
        System.out.println(inventories);
        return inventories;
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
        //deleteInventoryProductsTags(idInventory);
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products where Inventories_idInventory=" + idInventory + ";");
        rs.close();
        statement.close();
        connection.close();
    }

    public static void deleteProductTags(Product product) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products_Tags where Products_name='" + product.getName() + "';");
        rs.close();
        statement.close();
        connection.close();
    }

    public static void deleteProduct(Product product) throws SQLException {
        deleteProductTags(product);
        deleteProductInventoryProducts(product);
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Products where name='" + product + "';");
        rs.close();
        statement.close();
        connection.close();
    }

    private static void deleteProductInventoryProducts(Product product) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("delete from Inventories_Products where Products_name='" + product + "';");
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

    public static void updateInventory(Inventory inventory) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("update Inventories set name='" + inventory.getName() + "', full=" + inventory.getFull() + " where idInventory='" + inventory.getIdInventory() + "';");
        rs.close();
        statement.close();
        connection.close();
    }

    public static List<Product> getInventoryProducts(int inventoryId, String productName) throws SQLException {
        List<Product> products = new LinkedList<>();
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select Products.*, quantity, Products_Tags.tag from Products left join Inventories_Products on Inventories_Products.Products_name=Products.name inner join Products_Tags on `Products_Tags`.`Products_name`=Products.name where Inventories_Products.Inventories_idInventory=" + inventoryId + " and Products.name like '%" + productName + "%';");
        while(rs.next()) {
            boolean wasAlreadyInstantiated = false;
            for (Product p: products) {
                if (p.getName().equals(rs.getString("name"))) {
                    LinkedList<Product> tagProducts = new LinkedList<>();
                    tagProducts.add(p);
                    p.addTags(new Tag(rs.getString("tag"), tagProducts));
                    wasAlreadyInstantiated = true;
                    break;
                }
            }
            if (!wasAlreadyInstantiated) {
                Product p = new Product(rs.getString("name"), rs.getString("description"), rs.getString("productType"), new Company(rs.getString("fk_companyName")));
                p.addInventoryProduct(new InventoryProduct(new Inventory(inventoryId), p, rs.getInt("quantity")));
                products.add(p);
                LinkedList<Product> tagProducts = new LinkedList<>();
                tagProducts.add(p);
                p.addTags(new Tag(rs.getString("tag"), tagProducts));
            }

        }
        rs.close();
        statement.close();
        connection.close();
        return products;
    }

    public static List<Tag> getProductTags(Product product) throws SQLException {
        List<Tag> tags = new LinkedList<>();
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select Products_Tags.* from Products_Tags where Products_Tags.Products_name = '" + product.getName() + "';");
        while(rs.next())
            tags.add(new Tag(rs.getString("tag"), Collections.singletonList(product)));
        rs.close();
        statement.close();
        connection.close();
        return tags;
    }

    public static void insertProduct(Product product) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("insert into Products values('" + product.getName() + "', '" + product.getDescription() + "', '" + product.getProductType() + "' , '" + product.getManufacturer().getName() + "' );");
        rs.close();
        statement.close();
        connection.close();
        InventoryProduct[] ips = product.getInventoryProducts();
        for (InventoryProduct ip: ips) {
            connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
            statement = connection.createStatement();
            rs = statement.executeQuery("insert into Inventories_Products values(" + ip.getInventory().getIdInventory() +
                    ", '" + ip.getProduct().getName() + "' ," + ip.getQuantity() + ");");
            rs.close();
            statement.close();
            connection.close();
        }
    }

    public static List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new LinkedList<>();
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Companies");
        while(rs.next()) {
            companies.add(new Company(rs.getString("name"), rs.getString("CAP"), rs.getString("phoneNumber"), rs.getString("email")));
        }
        rs.close();
        statement.close();
        connection.close();
        return companies;
    }

    public static void insertTag(String tag, Product product) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("insert into Products_Tags values('" + product.getName() + "', '" + tag + "')");
        rs.close();
        statement.close();
        connection.close();
    }

    public static void updateProduct(Product product) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("update Products set description='" + product.getDescription() + "', productType='" + product.getProductType() + "', fk_companyName='"+ product.getManufacturer().getName() + "' where name='" + product.getName() + "';");
        rs.close();
        statement.close();
        connection.close();
    }

    public static void insertCompany(Company company) throws SQLException {
        Connection connection = DriverManager.getConnection(url, credentials.getUsername(), credentials.getPassword());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("insert into Companies values('" + company.getName() + "', '" + company.getCAP() + "', '" + company.getPhoneNumber() + "', '" + company.getEmail() + "' )");
        rs.close();
        statement.close();
        connection.close();
    }
}