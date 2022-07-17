package DataAccess;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {

    private static SingletonConnexion UniqueInstance;
    private static Connection connection;
    private static String password;

    private SingletonConnexion(){

    }

    public static SingletonConnexion getInstance() throws SQLException {
        if (UniqueInstance == null) {
            UniqueInstance = new SingletonConnexion();
            do {
                try {
                    password = JOptionPane.showInputDialog("Mot de passe de la base de donnée : ");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet java magasin", "root", password);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }while(connection == null);
        }
            return UniqueInstance;
    }

    public static Connection getConnection(){
        return connection;
    }


    }


