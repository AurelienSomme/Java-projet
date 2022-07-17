package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonOptionalDate implements ActionListener {

    private JComboBox<String> prodDay, prodMonth, prodYear = new JComboBox<>();
    private JCheckBox checkBox;

    public ButtonOptionalDate(JComboBox<String> prodDay, JComboBox<String> prodMonth, JComboBox<String>prodYear, JCheckBox checkBox){
        this.prodDay = prodDay;
        this.prodMonth = prodMonth;
        this.prodYear = prodYear;
        this.checkBox = checkBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            prodDay.setEnabled(true);
            prodMonth.setEnabled(true);
            prodYear.setEnabled(true);
        }
        else{
            prodDay.setEnabled(false);
            prodMonth.setEnabled(false);
            prodYear.setEnabled(false);
            prodDay.setSelectedIndex(0);
            prodMonth.setSelectedIndex(0);
            prodYear.setSelectedIndex(0);
        }
    }
}
