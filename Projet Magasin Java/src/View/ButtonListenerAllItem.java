package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerAllItem implements ActionListener {
    private JPanel panel;
    private JPanel newPanel;
    private JFrame frame;

    public ButtonListenerAllItem(JPanel panel, JFrame frame){
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        AllItemFrame frame = new AllItemFrame();

    }
}
