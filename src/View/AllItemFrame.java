package View;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AllItemFrame extends JFrame {
    JPanel newPanel;

    public AllItemFrame(){
        super("Stock");

        setBounds(80, 200, 1100, 800);
        setResizable(false);

        setBackground(new Color(0, 30, 50));

        setVisible(true);

        try {
            newPanel = new AllItemsPanel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        add(newPanel);
    }
}
