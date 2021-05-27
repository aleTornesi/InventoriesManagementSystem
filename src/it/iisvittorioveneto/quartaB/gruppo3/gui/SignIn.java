package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.User;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SignIn extends JFrame {
    private JTextField usernameTextField;
    private JTextField emailTextField;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JPasswordField passwordField;
    private JButton LoginRetButton;
    private JButton SignInButton;
    private JPanel contentPane;
    private JLabel errorLabel;
    private JPasswordField confirmPasswordTextField;

    public SignIn() {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        LoginRetButton.addActionListener(e -> {
            this.dispose();
            new Login();
        });

        SignInButton.addActionListener(e -> {
            try {
                System.out.println("email: " + this.emailTextField.getText());
                Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"); //email regex
                if (pattern.matcher(this.emailTextField.getText()).matches()) { //check if the email is valid
                    System.out.println("username: " + this.usernameTextField.getText());
                    if (!this.usernameTextField.getText().equals("")){
                        if (Arrays.equals(this.passwordField.getPassword(), this.confirmPasswordTextField.getPassword())) {
                            if (this.passwordField.getPassword() != null) {
                                if (this.passwordField.getPassword().length > 5) {
                                    String email = this.emailTextField.getText();
                                    String password = new String(passwordField.getPassword());
                                    String username = this.usernameTextField.getText();
                                    JDBC.signUp(email, password, username);
                                    this.dispose();
                                    new HomePage(new User(email, DigestUtils.sha1Hex(password), username));

                                }
                                else {
                                    this.errorLabel.setText("The password must be long at least 6 characters");
                                }
                            }
                            else {
                                this.errorLabel.setText("You must insert a password");
                            }
                        }
                        else {
                            this.errorLabel.setText("The two password don't match");
                        }

                    }
                    else {
                        this.errorLabel.setText("You must insert a username");
                    }
                }
                else {
                    this.errorLabel.setText("This email has an incorrect format");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
