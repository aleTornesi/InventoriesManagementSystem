package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.sql.SQLException;

public class Login {
    private JPanel contentPane;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signInButton;

    public Login() {
        logInButton.addActionListener(e -> {
            try {
                String username = JDBC.logIn(emailTextField.getText(), passwordField.getPassword());
                if (username != null);
                    //TODO go to home page
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }


    public JPasswordField getPasswordField() {
        return passwordField;
    }


    public JButton getLogInButton() {
        return logInButton;
    }


    public JButton getSignInButton() {
        return signInButton;
    }

}
