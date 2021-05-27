package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signInButton;
    private JLabel noUserFoundLabel;
    private JLabel invalidEmailLabel;

    public Login() {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        logInButton.addActionListener(e -> {
            try {
                Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"); //email regex
                if (pattern.matcher(emailTextField.getText()).matches()) { //check that the email is valid
                    User user = JDBC.logIn(emailTextField.getText(), new String(passwordField.getPassword()));
                    if (user != null){
                        this.dispose();
                        new HomePage(user);
                    }
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
            this.dispose(); //closes this JFrame
            new SignIn();
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
