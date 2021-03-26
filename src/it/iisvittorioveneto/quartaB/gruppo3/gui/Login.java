package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Login {
    private JPanel contentPane;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signInButton;
    private JLabel noUserFoundLabel;
    private JLabel invalidEmailLabel;

    public Login() {
        logInButton.addActionListener(e -> {
            try {
                Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
                if (pattern.matcher(emailTextField.getText()).matches()) {
                    String username = JDBC.logIn(emailTextField.getText(), passwordField.getPassword());
                    if (username != null);
                    //TODO go to home page
                    else{
                        invalidEmailLabel.setText("");
                        noUserFoundLabel.setText("There is no user with those credentials");
                    }

                }
                else {
                    noUserFoundLabel.setText("");
                    invalidEmailLabel.setText("This email has an incorrect format");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        signInButton.addActionListener(e -> {
            //TODO go to sign in form
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
