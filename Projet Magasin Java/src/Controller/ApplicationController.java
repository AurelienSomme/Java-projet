package Controller;

import BusinessLogic.ItemManager;
import Model.AddBookCodeException;
import Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationController {

    private ItemManager manager;

    public ApplicationController() throws SQLException {
        setManager(new ItemManager());
    }

    public void setManager(ItemManager manager){
        this.manager = manager;
    }

    public ArrayList<Item> getAllItems() throws SQLException {
        return manager.getAllItems();
    }

    public void addItem(Item item) throws SQLException {
        manager.addItem(item);
    }

    public void deleteItem(String code) throws SQLException {
        manager.deleteItem(code);
    }


}
