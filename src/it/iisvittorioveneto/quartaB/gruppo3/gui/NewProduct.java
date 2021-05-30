package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.*;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NewProduct extends JFrame {
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField productTypeTextField;
    private JButton JButtonOk;
    private JButton JButtonUndo;
    private JComboBox<Company> companyInput;
    private JButton addCompanyButton;
    private JTextField tagTextField;
    private JButton insertTagButton;
    private JPanel TagPanel;
    private JPanel tagsContainer;
    private JLabel errorLbl;
    private JTextField quantityTextField;
    private final List<String> insertedTags;

    public NewProduct(Inventory inventory, Product product, InventoryPage inventoryPage) {
        this.setContentPane(this.contentPane);
        this.setSize(600, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.tagsContainer.setLayout(new GridLayout(0, 4));

        this.insertedTags = new LinkedList<>();

        this.nameTextField.setText(product.getName());
        this.nameTextField.setEditable(false);
        this.descriptionTextField.setText(product.getDescription());
        this.productTypeTextField.setText(product.getProductType());
        for (InventoryProduct ip: product.getInventoryProducts()) {
            if (ip.getInventory().equals(inventory)) {
                this.quantityTextField.setText(String.valueOf(ip.getQuantity()));
                break;
            }
        }


        JButtonUndo.addActionListener(e -> this.dispose());

        JButtonOk.addActionListener(e -> {
            if (!this.nameTextField.getText().equals("") && !this.descriptionTextField.getText().equals("") &&
                    !this.productTypeTextField.getText().equals("") && !this.quantityTextField.getText().equals("") &&
                    this.companyInput.getSelectedItem() != null) {
                try {
                    List<Tag> tags = new LinkedList<>();
                    for (String t: this.insertedTags) {
                        tags.add(new Tag(t, new LinkedList<>()));
                    }
                    Product createdProduct = new Product(
                            this.nameTextField.getText(),
                            this.descriptionTextField.getText(),
                            this.productTypeTextField.getText(),
                            (Company) this.companyInput.getSelectedItem(),
                            new Inventory[]{inventory},
                            new int[]{Integer.parseInt(this.quantityTextField.getText())},
                            tags
                    );
                    JDBC.updateProduct(createdProduct);
                    JDBC.deleteProductTags(product);
                    for (String t: this.insertedTags) {
                        JDBC.insertTag(t, createdProduct);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                inventoryPage.getProductsList();
                inventoryPage.pack();
                this.dispose();
            } else {
                this.errorLbl.setText("All fields must be filled in");
            }
        });

        addCompanyButton.addActionListener(e -> {
            new NewCompany(this);
        });

        DefaultComboBoxModel<Company> comboBoxModel = new DefaultComboBoxModel<>();
        this.companyInput.setModel(comboBoxModel);
        try {
            comboBoxModel.addAll(JDBC.getAllCompanies());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            if (comboBoxModel.getElementAt(i).getName().equals(product.getManufacturer().getName())) {
                this.companyInput.setSelectedIndex(i);
            }
        }

        this.insertTagButton.addActionListener(
                e -> {
                    if (!this.tagTextField.getText().equals("") && !this.insertedTags.contains(this.tagTextField.getText())) {
                        System.out.println("insertTagButton clicked");
                        this.insertedTags.add(this.tagTextField.getText());
                        this.updateTagsContainerElements();
                    }
                }
        );
        for (Tag t: product.getTags()) {
            this.insertedTags.add(t.getTag());
        }
        updateTagsContainerElements();
    }

    public NewProduct(Inventory inventory, InventoryPage parent) {
        this.setContentPane(this.contentPane);
        this.setSize(600, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.tagsContainer.setLayout(new GridLayout(0, 4));

        this.insertedTags = new LinkedList<>();


        JButtonUndo.addActionListener(e -> this.dispose());

        JButtonOk.addActionListener(e -> {
            if (!this.nameTextField.getText().equals("") && !this.descriptionTextField.getText().equals("") &&
                    !this.productTypeTextField.getText().equals("") && !this.quantityTextField.getText().equals("") &&
                    this.companyInput.getSelectedItem() != null) {
                try {
                    List<Tag> tags = new LinkedList<>();
                    for (String t: this.insertedTags) {
                        tags.add(new Tag(t, new LinkedList<>()));
                    }
                    Product createdProduct = new Product(
                            this.nameTextField.getText(),
                            this.descriptionTextField.getText(),
                            this.productTypeTextField.getText(),
                            (Company) this.companyInput.getSelectedItem(),
                            new Inventory[]{inventory},
                            new int[]{Integer.parseInt(this.quantityTextField.getText())},
                            tags
                    );
                    JDBC.insertProduct(createdProduct);
                    for (String t: this.insertedTags) {
                        JDBC.insertTag(t, createdProduct);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                parent.getProductsList();
                parent.pack();
                this.dispose();
            } else {
                this.errorLbl.setText("All fields must be filled in");
            }
        });

        addCompanyButton.addActionListener(e -> {
            new NewCompany(this);
        });

        DefaultComboBoxModel<Company> comboBoxModel = new DefaultComboBoxModel<>();
        this.companyInput.setModel(comboBoxModel);
        try {
            comboBoxModel.addAll(JDBC.getAllCompanies());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.insertTagButton.addActionListener(
                e -> {
                    if (!this.tagTextField.getText().equals("") && !this.insertedTags.contains(this.tagTextField.getText())) {
                        System.out.println("insertTagButton clicked");
                        this.insertedTags.add(this.tagTextField.getText());
                        this.updateTagsContainerElements();
                    }
                }
        );
    }

    public void updateTagsContainerElements() {
        this.tagsContainer.removeAll();
        this.tagsContainer.revalidate();
        this.tagsContainer.repaint();
        System.out.println("tagContainer unfilled");
        for (String t : this.insertedTags) {
            JPanel jPanel = new JPanel();
            jPanel.add(new JLabel(t));
            JButton deleteButton = new JButton("x");
            deleteButton.addActionListener(e -> {
                this.insertedTags.remove(t);
                this.updateTagsContainerElements();
            });
            jPanel.add(deleteButton);
            this.tagsContainer.add(jPanel);
            this.pack();
        }
    }

    public void updateSelectedCompany(String name) {
        DefaultComboBoxModel<Company> comboBoxModel = new DefaultComboBoxModel<>();
        this.companyInput.setModel(comboBoxModel);
        try {
            comboBoxModel.addAll(JDBC.getAllCompanies());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            if (comboBoxModel.getElementAt(i).getName().equals(name)) {
                this.companyInput.setSelectedIndex(i);
                break;
            }
        }
    }
}
