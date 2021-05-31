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
    private JButton JButtonNewInventory;
    private JPanel list;
    private JButton newInventoryButton;
    private JLabel usersInventoriesLbl;
    private final User user;

    public HomePage(User user) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.user = user;
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        this.newInventoryButton.addActionListener(e -> new JDialog(new NewInventory(this, user)));
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
        this.getInventoriesList();
        this.usersInventoriesLbl.setText(user.getUsername() + "'s inventories");
        this.pack();
    }

    public void getInventoriesList(String name) {
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
                        new NewInventory(this, inventory);
                    }
                );
                jpanel.add(updateButton);
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e -> {
                    try {
                        JDBC.deleteInventory(inventory.getIdInventory());
                        this.getInventoriesList(name);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this,
                                "We're having some kind of problem");
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
                this.pack();
            }
        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,
                    "We're having some kind of problem");
        }

    }

    public void getInventoriesList() {
        this.getInventoriesList("");
    }
}