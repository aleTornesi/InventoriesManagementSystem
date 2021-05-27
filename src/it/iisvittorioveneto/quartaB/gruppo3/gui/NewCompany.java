package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;

public class NewCompany extends JFrame{
    private JPanel contentPane;
    private JLabel JLabelNewComapany;
    private JTextField JTextFieldName;
    private JButton JButtonOK;
    private JButton JButtonUndo;
    private JTextField JTextFieldCAP;
    private JTextField JTextFieldPhoneNumber;
    private JTextField JTextFieldEmail;

    public NewCompany() {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
