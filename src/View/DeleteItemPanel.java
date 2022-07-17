package View;

import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteItemPanel extends JPanel {

    private ApplicationController controller;
    private JPanel panelForm;
    private JLabel instruction, code;
    private JTextField codeText;
    private JButton deleteButton;

    public DeleteItemPanel() throws SQLException {
        setController(new ApplicationController());
        this.setBackground(new Color(0, 30, 50));
        panelForm = new JPanel();
        panelForm.setBackground(new Color(0, 30, 50));
        panelForm.setLayout(new GridLayout(1, 2));

        instruction = new JLabel("Entrez le code de l'article que vous voulez supprimer");
        code = new JLabel("Code");
        instruction.setForeground(Color.WHITE);
        instruction.setBackground(new Color(0, 30, 50));
        code.setForeground(Color.WHITE);
        code.setBackground(new Color(0, 30, 50));
        codeText = new JTextField();
        codeText.setForeground(Color.WHITE);
        codeText.setBackground(new Color(0, 30, 50));

        deleteButton = new JButton("Supprimer");
        deleteButton.setBackground(new Color(0, 30, 50));
        deleteButton.setForeground(Color.WHITE);
        ButtonListenerDeleteItem buttonListenerDeleteItem = new ButtonListenerDeleteItem(codeText, controller);
        deleteButton.addActionListener(buttonListenerDeleteItem);

        
        setLayout(new GridLayout(3, 1));

        panelForm.add(code);
        panelForm.add(codeText);
        add(instruction);
        add(panelForm);
        add(deleteButton);

    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
