package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Product;
import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Tag;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ProductPage extends JFrame{
    private JPanel contentPane;
    private JLabel productNameLabel;
    private JLabel productDescriptionLabel;
    private JLabel productTypeLabel;
    private JPanel tagContainer;
    private JLabel manufacturerLabel;

    public ProductPage(Product product){
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.productNameLabel.setText(product.getName());
        this.productDescriptionLabel.setText(product.getDescription());
        this.productTypeLabel.setText(product.getProductType());
        this.manufacturerLabel.setText(product.getManufacturer().getName());
        this.tagContainer.setLayout(new GridLayout(0, 7));
        /*try {
            product.setTags(JDBC.getProductTags(product));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        for (Tag t : product.getTags())
            tagContainer.add(new JLabel(t.getTag()));
        this.pack();
    }
}
