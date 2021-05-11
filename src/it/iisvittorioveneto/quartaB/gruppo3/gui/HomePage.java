package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JTextField inventoriesTextField;
    private JList<JLabel> inventoriesList;
    private JButton JButtonNewInventory;
    private User user;

    public HomePage(User user) {
        System.out.println(user.getEncryptedPassword());
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.user = user;
        this.inventoriesTextField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {}

                    @Override
                    public void removeUpdate(DocumentEvent e) {}

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        try {
                            List<Inventory> inventories = JDBC.getInventories(user, inventoriesTextField.getText());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        JButtonNewInventory.addActionListener(e ->{
            this.dispose();
            new NewInventory();
        });
    }
}
