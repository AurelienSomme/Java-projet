package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerNewPanel implements ActionListener {

    private JPanel panel;
    private JPanel newPanel;
    private JFrame frame;

    public ButtonListenerNewPanel(JPanel panel, JPanel newPanel, JFrame frame){
        this.panel = panel;
        this.newPanel = newPanel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        panel.removeAll();
        panel.add(newPanel);
        panel.revalidate();
        frame.revalidate();
    }
}
