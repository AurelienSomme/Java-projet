package View;

import javax.swing.*;

public class MovementThread extends Thread{

    private ImageShop[] imageShops;
    private Footer footer;
    private JFrame frame;
    private final static int RIGHT = 0;
    private final static int LEFT = 1;
    private final static int STATIC = 2;

    private int numberActual;

    public MovementThread(ImageShop[] imageShops, Footer footer, JFrame frame){
        super("Mouvement Thread Magasin");
        this.imageShops = imageShops;
        this.footer = footer;
        this.frame = frame;
        numberActual = 0;
    }

    public void run(){



        while(true){
            try{
                sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if(imageShops[numberActual].getDirection() == RIGHT){
                if(!borderReach(imageShops[numberActual])){
                    imageShops[numberActual].move();
                    footer.repaint();
                }
                else{
                    if(numberActual < 7) {
                        int width = frame.getSize().width - 80;
                        numberActual++;
                        footer.setImageActual(numberActual, width);
                    }
                    else{
                        numberActual = 0;
                        footer.setImageActual(numberActual, 0);
                    }

                    footer.repaint();
                }
            }

            else if(imageShops[numberActual].getDirection() == LEFT){
                if(!borderReach(imageShops[numberActual])){
                    imageShops[numberActual].move();
                    footer.repaint();
                }
                else {
                    if (numberActual < 7) {
                        numberActual++;
                        footer.setImageActual(numberActual, 0);
                    } else {
                        numberActual = 0;
                        footer.setImageActual(numberActual, 0);
                    }

                    footer.repaint();
                }
            }

            if(numberActual < 8) {
                if (imageShops[numberActual].getDirection() == STATIC) {
                    numberActual++;
                    if(numberActual == 8)
                        numberActual = 0;
                    int x = imageShops[numberActual].getDirection() == LEFT ? frame.getSize().width - 80 : 0;
                    footer.setImageActual(numberActual, x);
                }
            }
            else
                numberActual = 0;

        }
    }

    public boolean borderReach(ImageShop image){

        int width = frame.getSize().width;

        if(image.getDirection() == RIGHT){
            return image.getPositionX() >= width - 80;
        }
        else if(image.getDirection() == LEFT){
            return image.getPositionX() <= 0;
        }
        else{
            return false;
        }
    }
}
