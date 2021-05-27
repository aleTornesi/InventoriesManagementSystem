package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Product;

import javax.swing.*;
import java.awt.*;

public class NewProduct extends JFrame {
    private JPanel contentPane;
    private JLabel LabelNewProduct;
    private JTextField JTextFieldName;
    private JTextField JTextFieldDescription;
    private JTextField JTextFiledProductField;
    private JButton JButtonOk;
    private JButton JButtonUndo;
    private JComboBox comboBox1;
    private JButton JButtonAddCompany;
    private JTextField JTextFieldTag;
    private JButton JButtonInsertTag;
    private JTextArea JTextAreaTags;
    private JPanel TagPanel;

    public NewProduct(Product product){
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        JButtonUndo.addActionListener(e ->{
            this.dispose();
            new InventoryPage(null);
        });

        JButtonOk.addActionListener(e ->{
            this.dispose();
            new InventoryPage(null);
        });

        JButtonAddCompany.addActionListener(e -> {
            new NewCompany();
        });
    }

    public NewProduct() {
        this(null);
    }
}
