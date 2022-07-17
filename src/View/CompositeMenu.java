package View;

import javax.swing.*;
import java.util.ArrayList;

public class CompositeMenu extends ComposantMenu{

    private ArrayList<ComposantMenu> composants = new ArrayList<>();
    private JMenu menu;

    public CompositeMenu(JMenu menu){
        this.menu = menu;
    }

    public void view(){
        for(ComposantMenu composant : composants){
            composant.view();
        }
    }

    public void add(ComposantMenu composantMenu){
        composants.add(composantMenu);
    }

    public void delete(ComposantMenu composantMenu){
        composants.remove(composantMenu);
    }

    public ComposantMenu accesChild(int indice){
        return composants.get(indice);
    }
}
