package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import org.jetbrains.annotations.NotNull;

public class NewInventory extends JFrame {
    private JPanel contentPane;
    private JLabel newInventoryLabel;
    private JTextField nameTextField;
    private JButton JButtonOk;
    private JButton JButtonUndo;
    private JTextField fullTextField;
    private JLabel errorLbl;

    public NewInventory(HomePage parent, @NotNull Inventory inventory) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);

        this.setupTextFieldsUp(inventory);

        JButtonUndo.addActionListener(e -> {
            this.dispose();
        });

        JButtonOk.addActionListener(e -> {
            if(this.nameTextField.getText().length() > 0) {
                if(Float.parseFloat(this.fullTextField.getText()) >= 0 && Float.parseFloat(this.fullTextField.getText()) <= 100) {
                    Inventory newInventory = new Inventory(
                            inventory.getIdInventory(),
                            inventory.getOwner(),
                            this.nameTextField.getText(),
                            Float.parseFloat(this.fullTextField.getText())
                    );
                    try {
                        JDBC.updateInventory(newInventory);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this,
                                "We're having some kind of problem");
                    }

                    this.dispose();
                    parent.getInventoriesList();
                }
                else {
                    this.errorLbl.setText("The full value inserted is not a valid percentage");
                }
            }
            else {
                this.errorLbl.setText("An inventory must have a name");
            }

        });

    }

    public NewInventory(HomePage parent, User owner) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JButtonUndo.addActionListener(e -> {
            this.dispose();
        });

        JButtonOk.addActionListener(e -> {
            Inventory newInventory = new Inventory(
                    null,
                    owner,
                    this.nameTextField.getText(),
                    Float.parseFloat(this.fullTextField.getText())
            );
            try {
                JDBC.insertInventory(newInventory);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "We're having some kind of problem");
            }

            this.dispose();
            parent.getInventoriesList();
        });
    }

    private void setupTextFieldsUp(Inventory inventory) {
        this.nameTextField.setText(inventory.getName());
        this.fullTextField.setText(String.valueOf(inventory.getFull()));
    }
}
