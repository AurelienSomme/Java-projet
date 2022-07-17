package View;

import Controller.ApplicationController;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class ButtonListenerAddItem implements ActionListener {
    private JCheckBox optionalButtonReductionPoint, optionalButtonProductionDate;
    private ApplicationController controller;
    private Item item;

    private JTextField codeText,nameText, catalogPriceText, packagingText, vatText, stockQuantityText, thresholdLimitText, refBrandText, reductionPointsText;
    private JComboBox<String> prodDay, prodMonth, prodYear = new JComboBox<>();
    private JComboBox<String> saleDay, saleMonth, saleYear = new JComboBox<>();
    private JRadioButton yesAutomaticOrder;

    private String code;
    private int refBrand;
    private String name;
    private BigDecimal catalogPrice;
    private BigDecimal reductionPoints;
    private String packaging;
    private BigDecimal VAT;
    private BigDecimal stockQuantity;
    private BigDecimal thresholdLimit;
    private boolean automaticOrder;
    private GregorianCalendar productionDate;
    private GregorianCalendar saleDate;


    public ButtonListenerAddItem(ApplicationController controller, JCheckBox optionalButtonReductionPoint, JCheckBox optionalButtonProductionDate, JTextField codeText, JTextField refBrandText, JTextField nameText, JTextField catalogPriceText, JTextField reductionPointsText, JTextField packagingText, JTextField vatText, JTextField stockQuantityText, JTextField thresholdLimitText, JRadioButton yesAutomaticOrder, JComboBox prodYear, JComboBox prodMonth, JComboBox prodDay, JComboBox saleYear, JComboBox saleMonth, JComboBox saleDay){
        this.controller = controller;
        this.optionalButtonReductionPoint = optionalButtonReductionPoint;
        this.optionalButtonProductionDate = optionalButtonProductionDate;
        this.codeText = codeText;
        this.nameText = nameText;
        this.catalogPriceText = catalogPriceText;
        this.packagingText = packagingText;
        this.vatText = vatText;
        this.stockQuantityText = stockQuantityText;
        this.thresholdLimitText = thresholdLimitText;
        this.refBrandText = refBrandText;
        this.reductionPointsText = reductionPointsText;
        this.prodDay = prodDay;
        this.prodMonth = prodMonth;
        this.prodYear = prodYear;
        this.saleYear = saleYear;
        this.saleMonth = saleMonth;
        this.saleDay = saleDay;
        this.yesAutomaticOrder = yesAutomaticOrder;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        Boolean isAttributed = false;
        try {
            isAttributed = setAttributeItem();
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        if(isAttributed) {

            item = new Item(code, refBrand, name, catalogPrice, packaging, VAT, stockQuantity, thresholdLimit, automaticOrder, saleDate);

            if(optionalButtonReductionPoint.isSelected()){
                try {
                    if (reductionPointsText.getText().matches("[0-9]{1,}\\.{0,1}[0-9]{0,}")) {
                        reductionPoints = new BigDecimal(reductionPointsText.getText());
                        item.setReductionPoints(reductionPoints);
                    }
                    else
                        throw new AddBookBigDecimalException(reductionPointsText.getText(), "Points de réduction");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(optionalButtonProductionDate.isSelected()){
                productionDate = new GregorianCalendar(Integer.parseInt((String) prodYear.getSelectedItem()), Integer.parseInt((String) prodMonth.getSelectedItem()), Integer.parseInt((String) prodDay.getSelectedItem()));
                item.setProductionDate(productionDate);
            }


            try {
                controller.addItem(item);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean setAttributeItem() throws AddBookCodeException, AddBookIntException, AddBookBigDecimalException, AddBookStringBlankException {

        if(codeText.getText().matches("[0-9]+"))
            code = codeText.getText();
        else
            throw new AddBookCodeException(codeText.getText());

        if(codeText.getText().matches("[0-9]+"))
            refBrand = Integer.parseInt(refBrandText.getText());
        else
            throw new AddBookIntException(refBrandText.getText(), "Référence marque");

        if(!nameText.getText().isBlank())
            name = nameText.getText();
        else
            throw new AddBookStringBlankException("Nom");

        if(catalogPriceText.getText().matches("[0-9]{1,}\\.{0,1}[0-9]{0,}"))
            catalogPrice = new BigDecimal(catalogPriceText.getText());
        else
            throw new AddBookBigDecimalException(catalogPriceText.getText(), "Prix catalogue");

        if(!packagingText.getText().isBlank())
            packaging = packagingText.getText();
        else
            throw new AddBookStringBlankException("Emballage");

        if(vatText.getText().matches("[0-9]{1,}\\.{0,1}[0-9]{0,}"))
            VAT = new BigDecimal(vatText.getText());
        else
            throw new AddBookBigDecimalException(vatText.getText(), "TVA");

        if(stockQuantityText.getText().matches("[0-9]{1,}\\.{0,1}[0-9]{0,}"))
            stockQuantity = new BigDecimal(stockQuantityText.getText());
        else
            throw new AddBookBigDecimalException(stockQuantityText.getText(), "Quantité stock");

        if(thresholdLimitText.getText().matches("[0-9]{1,}\\.{0,1}[0-9]{0,}"))
            thresholdLimit = new BigDecimal(thresholdLimitText.getText());
        else
            throw new AddBookBigDecimalException(thresholdLimitText.getText(), "Seuil limite");

        automaticOrder = yesAutomaticOrder.isSelected();
        saleDate = new GregorianCalendar(Integer.parseInt((String)saleYear.getSelectedItem()), Integer.parseInt((String)saleMonth.getSelectedItem()), Integer.parseInt((String)saleDay.getSelectedItem()));
        return true;
    }
}
