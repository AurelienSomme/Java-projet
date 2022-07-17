package View;

import javax.swing.*;

public abstract class ComposantMenu {


    public void view(){throw new UnsupportedOperationException();}

    public void add(){throw  new UnsupportedOperationException();}

    public void delete(){throw new UnsupportedOperationException();}

    public ComposantMenu accesChild(int indice){
        throw new UnsupportedOperationException();
    }

}
