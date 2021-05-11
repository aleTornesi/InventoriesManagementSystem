package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;

public class NewInventory extends JFrame {
    private JPanel contentPane;
    private JLabel LabelNewInventory;
    private JTextField JTextFieldOwner;
    private JTextField JTextFieldName;
    private JButton JButtonOk;
    private JButton JButtonUndo;

    public NewInventory() {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /*
        JButtonUndo.addActionListener(e ->{
            this.dispose();
            new HomePage();
        });

        JButtonOk.addActionListener(e ->{
            this.dispose();
            new HomePage();
        });
        */

    }
}
