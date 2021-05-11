package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JTextField inventoriesTextField;
    private JPanel list;
    private User user;

    public HomePage(User user) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.user = user;
        this.getInventoriesList("");
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        this.inventoriesTextField.getDocument().addDocumentListener(
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
                        getInventoriesList(inventoriesTextField.getText());
                    }
                }
        );
    }

    private void getInventoriesList(String name) {
        this.list.removeAll();
        this.list.revalidate();
        this.list.repaint();
        try {
            List<Inventory> inventories = JDBC.getInventories(this.user, name);
            for (Inventory inventory: inventories) {
                JPanel jpanel = new JPanel();
                jpanel.add(new JLabel(inventory.getName()));
                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(e -> {
                    new NewInventory(inventory);
                }/*TODO call insert inventory page constructor */);
                jpanel.add(updateButton);
                JButton deleteButton = new JButton("Delete");
                updateButton.addActionListener(e -> {
                    try {
                        JDBC.deleteInventory(inventory.getIdInventory());
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
                        new InventoryPage(inventory);
                    }
                });
                this.list.add(jpanel);
            }
        } catch (SQLException | NullPointerException e) {}

    }
}