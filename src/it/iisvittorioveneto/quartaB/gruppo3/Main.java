package it.iisvittorioveneto.quartaB.gruppo3;

import it.iisvittorioveneto.quartaB.gruppo3.gui.Login;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Login");
        Login loginPage = new Login();
        jFrame.setContentPane(loginPage.getContentPane());
        jFrame.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width/2-jFrame.getSize().width/2, dim.height/2-jFrame.getSize().height/2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}
