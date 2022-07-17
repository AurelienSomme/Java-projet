package View;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    private ImageShop[] imageShops = new ImageShop[8];
    private int numberActual;
    private JFrame frame;
    private final static int RIGHT = 0;
    private final static int LEFT = 1;
    private final static int STATIC = 2;

    public Footer(ImageShop[] imageShops, JFrame frame){
        this.imageShops = imageShops;
        this.frame = frame;
        this.setLayout(new GridLayout(6, 0));
        numberActual = 0;
    }
    public void paint(Graphics g){

        super.paint(g);

        imageShops[numberActual].draw(g);

        if(numberActual < 7) {
            if (imageShops[numberActual + 1].getDirection() == STATIC) {

                if (imageShops[numberActual].getDirection() == RIGHT) {
                    imageShops[numberActual + 1].setPositionX(frame.getSize().width - 80);

                } else if (imageShops[numberActual].getDirection() == LEFT) {
                    imageShops[numberActual + 1].setPositionX(0);

                }

                imageShops[numberActual + 1].draw(g);
            }
        }
    }

    public void setImageActual(int numberActual, int x){
        this.numberActual = numberActual;
        imageShops[numberActual].setPositionX(x);
    }
}
