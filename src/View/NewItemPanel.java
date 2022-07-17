package View;



import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class NewItemPanel extends JPanel {

    private JPanel itemFormPanel;
    private JPanel optionalPanel;
    private JPanel buttonPanel;
    private JLabel code, name, catalogPrice, packaging, vat, stockQuantity, thresholdLimit, refBrand, automaticOrder, saleDate;
    private JLabel reductionPoint, productionDate;
    private JTextField codeText,nameText, catalogPriceText, packagingText, vatText, stockQuantityText, thresholdLimitText, refBrandText;
    private JRadioButton yesAutomaticOrder, noAutomaticOrder;
    private ButtonGroup groupAutomaticOrder;
    private JComboBox<String> saleDay, saleMonth, saleYear = new JComboBox<>();
    private JTextField reductionPointText;
    private JComboBox<String> prodDay, prodMonth, prodYear = new JComboBox<>();
    private JPanel orderPanel, dateSalePanel, dateProdPanel;
    private JCheckBox optionalButtonReductionPoint, optionalButtonProductionDate;
    private JButton addItem, reset;
    private ApplicationController controller;


    public NewItemPanel() throws SQLException {


        setController(new ApplicationController());


        code = new JLabel("Code");
        name = new JLabel("Nom");
        catalogPrice = new JLabel("Prix catalogue");
        packaging = new JLabel("emballage");
        vat = new JLabel("TVA");
        stockQuantity = new JLabel("Quantité stock");
        thresholdLimit = new JLabel("Seuil limite");
        refBrand = new JLabel("Référence de la marque");
        automaticOrder = new JLabel("Commande automatique");
        saleDate = new JLabel("Date de mise en vente");

        code.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        catalogPrice.setForeground(Color.WHITE);
        packaging.setForeground(Color.WHITE);
        vat.setForeground(Color.WHITE);
        stockQuantity.setForeground(Color.WHITE);
        thresholdLimit.setForeground(Color.WHITE);
        refBrand.setForeground(Color.WHITE);
        automaticOrder.setForeground(Color.WHITE);
        saleDate.setForeground(Color.WHITE);



        //Facultatif
        reductionPoint = new JLabel("Points de réduction");
        productionDate = new JLabel("Date de production");
        reductionPoint.setForeground(Color.WHITE);
        productionDate.setForeground(Color.WHITE);

        codeText = new JTextField();
        nameText = new JTextField();
        catalogPriceText = new JTextField();
        packagingText = new JTextField();
        vatText = new JTextField();
        stockQuantityText = new JTextField();
        thresholdLimitText = new JTextField();
        refBrandText = new JTextField();

        codeText.setBackground(new Color(0, 30, 50));
        codeText.setForeground(Color.WHITE);
        nameText.setBackground(new Color(0, 30, 50));
        nameText.setForeground(Color.WHITE);
        catalogPriceText.setBackground(new Color(0, 30, 50));
        catalogPriceText.setForeground(Color.WHITE);
        packagingText.setBackground(new Color(0, 30, 50));
        packagingText.setForeground(Color.WHITE);
        vatText.setBackground(new Color(0, 30, 50));
        vatText.setForeground(Color.WHITE);
        stockQuantityText.setBackground(new Color(0, 30, 50));
        stockQuantityText.setForeground(Color.WHITE);
        thresholdLimitText.setBackground(new Color(0, 30, 50));
        thresholdLimitText.setForeground(Color.WHITE);
        refBrandText.setBackground(new Color(0, 30, 50));
        refBrandText.setForeground(Color.WHITE);



        groupAutomaticOrder = new ButtonGroup();
        yesAutomaticOrder = new JRadioButton("Oui", true);
        yesAutomaticOrder.setBackground(new Color(0, 30, 50));
        yesAutomaticOrder.setForeground(Color.WHITE);
        noAutomaticOrder = new JRadioButton("Non");
        noAutomaticOrder.setBackground(new Color(0, 30, 50));
        noAutomaticOrder.setForeground(Color.WHITE);
        groupAutomaticOrder.add(yesAutomaticOrder);
        groupAutomaticOrder.add(noAutomaticOrder);

        String[] days = {"1", "2" ,"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" ,"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] years = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"};

        saleDay = new JComboBox<>(days);
        saleMonth =  new JComboBox<>(months);
        saleYear = new JComboBox<>(years);
        saleDay.setBackground(new Color(0, 30, 50));
        saleDay.setForeground(Color.WHITE);
        saleMonth.setBackground(new Color(0, 30, 50));
        saleMonth.setForeground(Color.WHITE);
        saleYear.setBackground(new Color(0, 30, 50));
        saleYear.setForeground(Color.WHITE);

        prodDay = new JComboBox<>(days);
        prodMonth = new JComboBox<>(months);
        prodYear = new JComboBox<>(years);
        prodDay.setEnabled(false);
        prodMonth.setEnabled(false);
        prodYear.setEnabled(false);

        prodDay.setBackground(new Color(0, 30, 50));
        prodDay.setForeground(Color.WHITE);
        prodMonth.setBackground(new Color(0, 30, 50));
        prodMonth.setForeground(Color.WHITE);
        prodYear.setBackground(new Color(0, 30, 50));
        prodYear.setForeground(Color.WHITE);

        dateSalePanel = new JPanel();
        dateSalePanel.setBackground(new Color(0, 30, 50));
        dateSalePanel.setLayout(new GridLayout(1, 3));
        dateSalePanel.add(saleDay);
        dateSalePanel.add(saleMonth);
        dateSalePanel.add(saleYear);

        dateProdPanel = new JPanel();
        dateProdPanel.setBackground(new Color(0, 30, 50));
        dateProdPanel.add(prodDay);
        dateProdPanel.add(prodMonth);
        dateProdPanel.add(prodYear);

        orderPanel = new JPanel();
        orderPanel.setBackground(new Color(0, 30, 50));
        orderPanel.setLayout(new GridLayout(1, 2));

        reductionPointText = new JTextField();
        reductionPointText.setBackground(new Color(0, 30, 50));
        reductionPointText.setForeground(Color.WHITE);
        reductionPointText.setEnabled(false);

        optionalPanel = new JPanel();
        optionalPanel.setBackground(new Color(0, 30, 50));


        optionalButtonReductionPoint = new JCheckBox("Oui");
        optionalButtonReductionPoint.setBackground(new Color(0, 30, 50));
        optionalButtonReductionPoint.setForeground(Color.WHITE);
        ButtonListenerOptionalPoint listenerCheckPoint = new ButtonListenerOptionalPoint(reductionPointText, optionalButtonReductionPoint);
        optionalButtonReductionPoint.addActionListener(listenerCheckPoint);
        optionalButtonProductionDate = new JCheckBox("Oui");
        optionalButtonProductionDate.setBackground(new Color(0, 30, 50));
        optionalButtonProductionDate.setForeground(Color.WHITE);
        ButtonOptionalDate listenerCheckDate = new ButtonOptionalDate(prodDay, prodMonth, prodYear, optionalButtonProductionDate);
        optionalButtonProductionDate.addActionListener(listenerCheckDate);

        optionalPanel.setLayout(new GridLayout(4, 2));
        JLabel reducPointOptText = new JLabel("Ajouter des points de réduction ?");
        reducPointOptText.setForeground(Color.WHITE);
        optionalPanel.add(reducPointOptText);
        optionalPanel.add(optionalButtonReductionPoint);
        optionalPanel.add(reductionPoint);
        optionalPanel.add(reductionPointText);
        JLabel prodDateOptText = new JLabel("Ajouter une date de production ?");
        prodDateOptText.setForeground(Color.WHITE);
        optionalPanel.add(prodDateOptText);
        optionalPanel.add(optionalButtonProductionDate);
        optionalPanel.add(productionDate);
        optionalPanel.add(dateProdPanel);

        itemFormPanel = new JPanel();
        itemFormPanel.setBackground(new Color(0, 30, 50));
        itemFormPanel.setLayout(new GridLayout(10, 2));

        itemFormPanel.add(code);
        itemFormPanel.add(codeText);

        itemFormPanel.add(name);
        itemFormPanel.add(nameText);

        itemFormPanel.add(catalogPrice);
        itemFormPanel.add(catalogPriceText);

        itemFormPanel.add(packaging);
        itemFormPanel.add(packagingText);

        itemFormPanel.add(vat);
        itemFormPanel.add(vatText);

        itemFormPanel.add(stockQuantity);
        itemFormPanel.add(stockQuantityText);

        itemFormPanel.add(thresholdLimit);
        itemFormPanel.add(thresholdLimitText);

        itemFormPanel.add(refBrand);
        itemFormPanel.add(refBrandText);

        itemFormPanel.add(automaticOrder);



        orderPanel.add(yesAutomaticOrder);
        orderPanel.add(noAutomaticOrder);

        itemFormPanel.add(orderPanel);


        itemFormPanel.add(saleDate);
        itemFormPanel.add(dateSalePanel);


        setLayout(new GridLayout(3, 1));


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));


        addItem = new JButton("Ajouter");
        ButtonListenerAddItem listenerAddItem = new ButtonListenerAddItem(controller, optionalButtonReductionPoint, optionalButtonProductionDate, codeText, refBrandText, nameText, catalogPriceText, reductionPointText, packagingText, vatText, stockQuantityText, thresholdLimitText, yesAutomaticOrder, prodYear, prodMonth, prodDay, saleYear, saleMonth, saleDay);
        addItem.addActionListener(listenerAddItem);
        reset = new JButton("Réinitialiser");

        buttonPanel.add(addItem);
        buttonPanel.add(reset);

        setLayout(new BorderLayout());
        add(itemFormPanel, BorderLayout.NORTH);
        add(optionalPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
