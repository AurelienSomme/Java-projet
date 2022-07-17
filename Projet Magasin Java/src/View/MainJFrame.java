package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class MainJFrame extends JFrame {
        private Container container;
        private Footer footer;
        private JPanel main;
        private ImageShop[] imageShops = new ImageShop[8];
        private JLabel welcomMessage;
        private JMenuBar menuBar;
        private JMenu menuItem, menuBrand;
        private JMenuItem allItem, changePromo, searchItem, addItem, deleteItem, allBrand, addBrand, deleteBrand;
        CompositeMenu generalMenu;
        public MainJFrame() throws IOException, SQLException {
                super("Appli");

                setBounds(80, 200, 1200, 800);


                //Fermer prog via bouton en haut à droite
                addWindowListener(new WindowAdapter() {
                        public void windowClosing (WindowEvent e){
                                System.exit(0);
                        }
                });

                setVisible(true);


                //Charger photos
                BufferedImage img = null;
                File path = new File("Icon");
                int i = 0;
                for (File file : path.listFiles()) {
                        try {
                                img = ImageIO.read(file);
                                if (img != null) {
                                        imageShops[i] = new ImageShop(0, 0, img);
                                        i++;
                                }
                        } catch (IOException e) {
                                continue;
                        }
                }
                imageShops[0].setDirection(0);
                imageShops[1].setDirection(2);
                imageShops[2].setDirection(1);
                imageShops[3].setDirection(2);
                imageShops[4].setDirection(0);
                imageShops[5].setDirection(1);
                imageShops[6].setDirection(0);
                imageShops[7].setDirection(1);


                container = getContentPane();
                footer = new Footer(imageShops, this);
                main = new JPanel();
                welcomMessage = new JLabel("Bienvenue sur l'application permettant de gérer les articles du magasin le brand bazard");
                welcomMessage.setForeground(Color.WHITE);
                main.add(welcomMessage);
                main.setBackground(new Color(0, 30, 50));



                //Permet de créer un zone plus grande pour les images
                JPanel canvas = new JPanel();
                canvas.setBackground(new Color(0, 30, 50));
                footer.add(canvas);
                footer.setBackground(new Color(0, 30, 50));
                menuBar = new JMenuBar();
                setJMenuBar(menuBar);
                menuItem = new JMenu("Article");
                allItem = new JMenuItem("Voir tous les articles");
                ButtonListenerAllItem allItemPanelListener = new ButtonListenerAllItem(main, this);
                allItem.addActionListener(allItemPanelListener);
                changePromo = new JMenuItem("Modifier les promos");
                searchItem = new JMenuItem("Rechercher des articles");
                addItem = new JMenuItem("Ajouter un nouvel article");
                ButtonListenerNewPanel newItemPanelListener = new ButtonListenerNewPanel(main, new NewItemPanel(), this);
                addItem.addActionListener(newItemPanelListener);
                deleteItem = new JMenuItem("Supprimer un article");
                ButtonListenerNewPanel deleteItemPanelListener = new ButtonListenerNewPanel(main, new DeleteItemPanel(), this);
                deleteItem.addActionListener(deleteItemPanelListener);

                menuBrand = new JMenu("Marque");
                allBrand = new JMenuItem("Voir toutes les marques");
                addBrand = new JMenuItem("Ajouter une marque");
                deleteBrand = new JMenuItem("Supprimer une marque");

                menuItem.add(allItem);
                menuItem.add(changePromo);
                menuItem.add(searchItem);
                menuItem.add(addItem);
                menuItem.add(deleteItem);

                menuBrand.add(allBrand);
                menuBrand.add(addBrand);
                menuBrand.add(deleteBrand);

                menuBar.add(menuItem);
                menuBar.add(menuBrand);





                generalMenu = new CompositeMenu(menuItem);
                generalMenu.add(new SheetMenu(allItem));
                generalMenu.add(new SheetMenu(changePromo));
                generalMenu.add(new SheetMenu(searchItem));
                generalMenu.add(new SheetMenu(addItem));
                generalMenu.add(new SheetMenu(deleteItem));



                container.add(footer, BorderLayout.SOUTH);
                container.add(main, BorderLayout.CENTER);

                //Permet le premier affichage
                this.revalidate();

                MovementThread movementImage = new MovementThread(imageShops, footer, this);
                movementImage.start();

        }





        public static void main(String[] args) throws IOException, SQLException {
                MainJFrame e = new MainJFrame();
        }
}
