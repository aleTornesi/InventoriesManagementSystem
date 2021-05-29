package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.InventoryProduct;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Product;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class InventoryPage extends JFrame {
    private JTextField productsTextField;
    private JPanel contentPane;
    private JPanel productList;
    private JButton addProductButton;
    private JLabel usersProductsLbl;
    private final Inventory inventory;

    public InventoryPage(Inventory inventory) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.inventory = inventory;
        this.productList.setLayout(new BoxLayout(this.productList, BoxLayout.PAGE_AXIS));
        this.productsTextField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        onUpdate();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        onUpdate();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        onUpdate();
                    }

                    public void onUpdate() {
                        getProductsList(productsTextField.getText());
                    }
                }
        );
        this.addProductButton.addActionListener(
                e -> {
                    new NewProduct(inventory);
                }
        );
        this.usersProductsLbl.setText(inventory.getName() + "'s products");
        this.getProductsList();
    }

    private void getProductsList(String name) {
        this.productList.removeAll();
        this.productList.revalidate();
        this.productList.repaint();
        try {
            List<Product> products = JDBC.getProducts(JDBC.getInventoryProducts(this.inventory.getIdInventory(), name));
            for (Product product: products) {
                JPanel jpanel = new JPanel();
                jpanel.add(new JLabel(product.getName()));
                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(e -> {
                    new NewProduct(inventory, product);
                });
                jpanel.add(updateButton);
                JButton deleteButton = new JButton("Delete");
                updateButton.addActionListener(e -> {
                    try {
                        JDBC.deleteProduct(product.getName());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                jpanel.add(deleteButton);
                jpanel.addMouseListener(new MouseAdapter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //TODO call constructor of ProductPage
                    }
                });
                this.productList.add(jpanel);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void getProductsList() {
        this.getProductsList("");
    }
}
