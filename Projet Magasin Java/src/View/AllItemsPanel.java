package View;

import Controller.ApplicationController;
import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AllItemsPanel extends JPanel {
    private ApplicationController controller;
    private JTable itemsTab;
    private ArrayList<Item> items = new ArrayList<>();
    private Object[][] itemsObjectTab;
    private String[] nameColumn = {"Code", "Ref marque", "Nom", "Prix catalogue", "Emballage", "TVA","Quantité stock", "Seuil limite", "Commande auto", "Date de mise en vente", "Points reduc", "Date de production"};

    public AllItemsPanel() throws SQLException {

        setController(new ApplicationController());
        setBackground(new Color(0, 30, 50));
        items = controller.getAllItems();



        itemsObjectTab = new Object[items.size()][nameColumn.length];

        for(int i = 0; i < items.size(); i++){
            itemsObjectTab[i][0] = items.get(i).getCode();
            itemsObjectTab[i][1] = items.get(i).getRefBrand();
            itemsObjectTab[i][2] = items.get(i).getName();
            itemsObjectTab[i][3] = items.get(i).getCatalogPrice();
            itemsObjectTab[i][4] = items.get(i).getPackaging();
            itemsObjectTab[i][5] = items.get(i).getVAT();
            itemsObjectTab[i][6] = items.get(i).getStockQuantity();
            itemsObjectTab[i][7] = items.get(i).getThresholdLimit();
            itemsObjectTab[i][8] = items.get(i).getAutomaticOrder();
            itemsObjectTab[i][9] = items.get(i).getSaleDate().getTime();
            if(items.get(i).getReductionPoints() != null)
                itemsObjectTab[i][10] = items.get(i).getReductionPoints();
            if(items.get(i).getProductionDate() != null)
                itemsObjectTab[i][11] = items.get(i).getProductionDate().getTime().toGMTString();
        }

        itemsTab = new JTable(itemsObjectTab, nameColumn);
        itemsTab.setBackground(new Color(0, 30, 50));
        itemsTab.setForeground(Color.WHITE);
        itemsTab.getTableHeader().setBackground(new Color(0, 30, 50));
        itemsTab.getTableHeader().setForeground(Color.WHITE);
        itemsTab.getColumnModel().getColumn(6).setPreferredWidth(85);
        itemsTab.getColumnModel().getColumn(3).setPreferredWidth(85);
        itemsTab.getColumnModel().getColumn(9).setPreferredWidth(130);
        itemsTab.getColumnModel().getColumn(11).setPreferredWidth(130);

        itemsTab.setEnabled(false);

        setLayout(new BorderLayout());
        JPanel head = new JPanel();
        JPanel main = new JPanel();
        head.setBackground(new Color(0, 30, 50));
        main.setBackground(new Color(0, 30, 50));

        head.add(itemsTab.getTableHeader());
        main.add(itemsTab);
        add(head, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
