package View;

import Controller.ApplicationController;
import Model.AddBookCodeException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ButtonListenerDeleteItem implements ActionListener {

    private String code;
    private JTextField codeText;
    private ApplicationController controller;

    public ButtonListenerDeleteItem(JTextField codeText, ApplicationController controller){
        this.codeText = codeText;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            setCode();
            try {
                controller.deleteItem(code);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setCode() throws AddBookCodeException{
        if(codeText.getText().matches("[0-9]+"))
            code = codeText.getText();
        else
            throw new AddBookCodeException(codeText.getText());
    }

}
