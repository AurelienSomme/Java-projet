package View;

import java.awt.*;

public class ImageShop {
    private int positionX;
    private int positionY;
    private Image image;
    private int direction;
    private final static int RIGHT = 0;
    private final static int LEFT = 1;
    private final static int STATIC = 2;

    public ImageShop(int positionX, int positionY, Image image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.image = image;
    }

    public void draw(Graphics g){

        g.drawImage(image, positionX, positionY, null);
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getDirection(){
        return direction;
    }



    public void move(){
        if(direction == RIGHT)
            positionX++;
        else if(direction == LEFT)
            positionX--;
    }



    public int getPositionX(){
        return positionX;
    }

    public void setPositionX(int x){
        positionX = x;
    }

}