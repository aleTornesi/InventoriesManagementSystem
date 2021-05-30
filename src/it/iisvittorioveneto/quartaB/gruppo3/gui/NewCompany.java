package it.iisvittorioveneto.quartaB.gruppo3.gui;

import it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem.Company;
import it.iisvittorioveneto.quartaB.gruppo3.mariadb.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class NewCompany extends JFrame{
    private JPanel contentPane;
    private JTextField nameTextField;
    private JButton okButton;
    private JButton undoButton;
    private JTextField CAPTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JLabel errorLabel;

    public NewCompany(NewProduct parent) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);

        this.okButton.addActionListener(e -> {
            if (!this.nameTextField.getText().equals("")) {
                Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");
                if (pattern.matcher(this.phoneNumberTextField.getText()).matches()) {
                    if (this.CAPTextField.getText().length() == 5) {
                        pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"); //email regex
                        if (pattern.matcher(this.emailTextField.getText()).matches()) {
                            try {
                                Company c = new Company(
                                        this.nameTextField.getText(),
                                        this.CAPTextField.getText(),
                                        this.phoneNumberTextField.getText(),
                                        this.emailTextField.getText()
                                );
                                JDBC.insertCompany(c);
                                parent.updateSelectedCompany(c.getName());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        else {
                            this.errorLabel.setText("Invalid email format");
                        }
                    }
                    else {
                        this.errorLabel.setText("Invalid CAP format");
                    }
                }
                else {
                    this.errorLabel.setText("Invalid phone number format");
                }
            }
            else {
                this.errorLabel.setText("The company must have a name");
            }
        });

    }

}
