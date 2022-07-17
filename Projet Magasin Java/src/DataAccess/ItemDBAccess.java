package DataAccess;

import Model.Brand;
import Model.Item;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ItemDBAccess implements ItemDataAccess{

    private SingletonConnexion singletonConnexion;
    private Connection connection;

    public ItemDBAccess() throws SQLException {
        singletonConnexion = SingletonConnexion.getInstance();
        connection = singletonConnexion.getConnection();
    }

    @Override
    public ArrayList<Item> getAllItems() throws SQLException {

        String getAllItemsInstruction = "select * from item;";

        PreparedStatement preparedStatement = connection.prepareStatement(getAllItemsInstruction);
        ResultSet data = preparedStatement.executeQuery();

        ArrayList<Item> allItems = new ArrayList<Item>();

        Item item;

        while (data.next()){

            GregorianCalendar gregProdDate = new GregorianCalendar();
            GregorianCalendar gregSaleDate = new GregorianCalendar();
            gregSaleDate.setTime(data.getDate("sale_date"));

            item = new Item(data.getString("code"),data.getInt("ref_brand"), data.getString("name"), data.getBigDecimal("catalog_price"), data.getString("packaging"), data.getBigDecimal("VAT"), data.getBigDecimal("stock_quantity"), data.getBigDecimal("threshold_limit"), data.getBoolean("automatic_order"), gregSaleDate);

            if(data.getDate("production_date") != null){
                gregProdDate.setTime(data.getDate("production_date"));
                item.setProductionDate(gregProdDate);
            }
            if(data.getBigDecimal("reduction_points") != null){
                item.setReductionPoints(data.getBigDecimal("reduction_points"));
            }

            allItems.add(item);
        }

        return allItems;
    }

    public ArrayList<Brand> getAllBrands() throws SQLException {
        ArrayList<Brand> allBrands = new ArrayList<>();

        String instruction = "select * from brand;";

        PreparedStatement preparedStatement = connection.prepareStatement(instruction);
        ResultSet data = preparedStatement.executeQuery();

        Brand brand = new Brand();

        while(data.next()){
            GregorianCalendar creationDateGreg = new GregorianCalendar();
            creationDateGreg.setTime(data.getDate("creation_date"));
            brand = new Brand(data.getInt("id_brand"), data.getString("name"), data.getString("CEO"), creationDateGreg);
            if(!data.getString("description").equals(null))
                brand.setDescription(data.getString("description"));
        }
        allBrands.add(brand);

        return allBrands;
    }

    @Override
    public void addItem(Item item) throws SQLException {
        ArrayList<Item> allItems = new ArrayList<Item>();
        allItems = getAllItems();
        int i = 0;
        while(i < allItems.toArray().length && Integer.parseInt(allItems.get(i).getCode()) != Integer.parseInt(item.getCode())){
            i++;
        }

        if(i == allItems.toArray().length){

            ArrayList<Brand> brands = getAllBrands();

            int y = 0;
            while(y < brands.toArray().length && item.getRefBrand() != brands.get(y).getIdBrand()){
                System.out.println("id brand de litem : " + item.getRefBrand() + "    id brand acutel : " + brands.get(y).getIdBrand());
                y++;
            }

            if(y == brands.toArray().length)
                System.out.println("Ref pas trouvée");
            else {

                String sqlInstruction = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

                preparedStatement.setInt(1, Integer.parseInt(item.getCode()));
                preparedStatement.setInt(2, item.getRefBrand());
                preparedStatement.setString(3, item.getName());
                preparedStatement.setBigDecimal(4, item.getCatalogPrice());
                if (item.getReductionPoints() != null)
                    preparedStatement.setBigDecimal(5, item.getReductionPoints());
                else
                    preparedStatement.setBigDecimal(5, null);
                preparedStatement.setString(6, item.getPackaging());
                preparedStatement.setBigDecimal(7, item.getVAT());
                preparedStatement.setBigDecimal(8, item.getStockQuantity());
                preparedStatement.setBigDecimal(9, item.getThresholdLimit());
                preparedStatement.setBoolean(10, item.getAutomaticOrder());
                if (item.getProductionDate() != null)
                    preparedStatement.setDate(11, new java.sql.Date(item.getProductionDate().getTimeInMillis()));
                else
                    preparedStatement.setDate(11, null);
                preparedStatement.setDate(12, new java.sql.Date(item.getSaleDate().getTimeInMillis()));


                int nbUpdatedLines = preparedStatement.executeUpdate();

            }



        }
        else{
            //Créer un exception pour quand le code existe deja dans la DB
        }
    }

    @Override
    public void deleteItem(String code) throws SQLException {
        String deleteInstruction = "DELETE FROM item where code = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteInstruction);
        preparedStatement.setString(1, code);
        int nbUpdatedLines = preparedStatement.executeUpdate();
        if(nbUpdatedLines == 0)
            JOptionPane.showMessageDialog(null, "Code pas trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
