package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class NewCompany extends JFrame{
    private JPanel contentPane;
    private JTextField nameTextField;
    private JButton okButton;
    private JButton undoButton;
    private JTextField CAPTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;

    public NewCompany(Object selectedItem) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.okButton.addActionListener(e -> {
            if (!this.nameTextField.getText().equals("")) {
                Pattern pattern = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
            }
            else {

            }
        });

    }

}
