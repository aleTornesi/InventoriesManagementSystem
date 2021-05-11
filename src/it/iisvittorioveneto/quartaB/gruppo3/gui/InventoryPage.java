package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;

public class InventoryPage extends JFrame{
    private JTextField InventoryTextField;
    private JList ProductList;
    private JPanel contentPane;
    private JButton JButtonNewProduct;

    public InventoryPage() {

        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JButtonNewProduct.addActionListener(e ->{
            //this.dispose();
            new NewProduct();
        });
    }
}
