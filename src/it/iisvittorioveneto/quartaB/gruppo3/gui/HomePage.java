package it.iisvittorioveneto.quartaB.gruppo3.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JTextField InventoriesTextField;
    private JList<JLabel> InventoriesList;
    private String userEmail;

    public HomePage(String userEmail) {
        this.setContentPane(this.contentPane);
        this.setSize(500, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //center the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.userEmail = userEmail;
        this.InventoriesTextField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                }
        );
    }
}
