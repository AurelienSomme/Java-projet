package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerOptionalPoint implements ActionListener {

    private JTextField textField;
    private JCheckBox checkBox;

    public ButtonListenerOptionalPoint(JTextField textField, JCheckBox checkBox){
        this.textField = textField;
        this.checkBox = checkBox;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(checkBox.isSelected()){
            textField.setEnabled(true);
        }
        else{
            textField.setEnabled(false);
            textField.setText("");
        }
    }
}
