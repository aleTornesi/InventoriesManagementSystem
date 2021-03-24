package it.iisvittorioveneto.quartaB.gruppo3;

import it.iisvittorioveneto.quartaB.gruppo3.gui.Login;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        Login loginPage = new Login();
        frame.setContentPane(loginPage.getContentPane());
        frame.setSize(500, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
