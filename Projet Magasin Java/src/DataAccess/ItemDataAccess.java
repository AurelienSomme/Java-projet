package DataAccess;

import Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDataAccess {

    ArrayList<Item> getAllItems() throws SQLException;

    void addItem(Item item) throws SQLException;

    void deleteItem(String code) throws SQLException;
}
