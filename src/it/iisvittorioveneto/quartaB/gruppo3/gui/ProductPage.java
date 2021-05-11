package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;

public class ProductPage extends JFrame{
    private JTextField InventoryTextField;
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField productTypeTextField;
    private JTextField descriptionTextField;

    public ProductPage(){
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
