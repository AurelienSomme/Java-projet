package BusinessLogic;

import DataAccess.ItemDBAccess;
import DataAccess.ItemDataAccess;
import Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemManager {

    private ItemDataAccess dao;


    public ItemManager() throws SQLException {
        setDao(new ItemDBAccess());
    }

    public void setDao(ItemDataAccess itemDBAccess){
        dao = itemDBAccess;
    }

    public ArrayList<Item> getAllItems() throws SQLException {
        return dao.getAllItems();
    }

    public void addItem(Item item) throws SQLException {
        dao.addItem(item);
    }

    public void deleteItem(String code) throws SQLException {
        dao.deleteItem(code);
    }

}
