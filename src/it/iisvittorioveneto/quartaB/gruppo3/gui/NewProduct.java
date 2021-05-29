package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Company;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Inventory;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Product;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NewProduct extends JFrame {
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField productTypeTextField;
    private JButton JButtonOk;
    private JButton JButtonUndo;
    private JComboBox<Company> companyInput;
    private JButton addCompanyButton;
    private JTextField JTextFieldTag;
    private JButton JButtonInsertTag;
    private JPanel TagPanel;
    private JScrollPane tagsContainer;
    private JLabel errorLbl;
    private JTextField quantityTextField;

    public NewProduct(Inventory inventory, Product product){
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        JButtonUndo.addActionListener(e -> this.dispose());

        JButtonOk.addActionListener(e ->{
            if (!this.nameTextField.getText().equals("") && !this.descriptionTextField.getText().equals("") &&
                    !this.productTypeTextField.getText().equals("") && !this.quantityTextField.getText().equals("") &&
                    this.companyInput.getSelectedItem() != null) {
                try {
                    JDBC.insertProduct(
                            new Product(
                                    this.nameTextField.getText(),
                                    this.descriptionTextField.getText(),
                                    this.productTypeTextField.getText(),
                                    (Company) this.companyInput.getSelectedItem(),
                                    new Inventory[] {inventory},
                                    new int[] {Integer.parseInt(this.quantityTextField.getText())}
                            )
                    );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            this.dispose();
        });

        addCompanyButton.addActionListener(e -> {
            new NewCompany(this.companyInput.getSelectedItem());
        });

        DefaultComboBoxModel<Company> comboBoxModel = new DefaultComboBoxModel<>();
        this.companyInput.setModel(comboBoxModel);
        try {
            comboBoxModel.addAll(JDBC.getAllCompanies());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public NewProduct(Inventory inventory) {
        this(inventory,null);
    }
}
