package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;

public class SignIn extends JFrame {
    private JTextField name;
    private JTextField lastName;
    private JTextField email;
    private JLabel LabelName;
    private JLabel LabelEmail;
    private JLabel LabelLName;
    private JPasswordField password;
    private JButton LoginRetButton;
    private JButton SignInButton;
    private JPanel contentPane;

    public SignIn() {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}