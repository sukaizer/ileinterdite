package ile.controller;

import ile.model.*;
import ile.view.MenuView;
import ile.view.StartView;
import ile.view.View;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuController implements MouseInputListener {
    Model model;
    StartView view;
    MenuView menu;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;

    private boolean p1 = false;
    private boolean p2 = false;
    private boolean p3 = false;
    private boolean p4 = false;
    private boolean p5 = false;
    private boolean p6 = false;

    /**
     * Constructeur de classe MenuController
     * @param model le modèle lié au programme
     * @param view la vue principale comprenant toutes autres
     * @param menu la vue correspondant au menu de choix des personnages
     */
    public MenuController(Model model, StartView view,MenuView menu){
        this.model = model;
        this.view = view;
        this.menu = menu;
    }

    /**
     * Activation du click pour choisir les joueurs dans le menu
     * @param e click de la souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        menu.getParent().repaint();
        int x = e.getX();
        int y = e.getY();
        if (x >= MenuView.WIDTH / 12 && x <= MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p1) {
            if (count1 == 0) {
                count1++;
                count2 = 0;
                count3 = 0;
                count4 = 0;
                count5 = 0;
                count6 = 0;
                menu.description.setText("Peut se déplacer et assécher en diagonale");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerExplorator(model));
                    this.p1 = true;
                }else{
                    model.addPlayer(new PlayerExplorator(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (x >= 3 * MenuView.WIDTH / 12 && x <= 3 * MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p2) {
            if (count2 == 0) {
                count2++;
                count1 = 0;
                count3 = 0;
                count4 = 0;
                count5 = 0;
                count6 = 0;
                menu.description.setText("Peut assécher deux zones pour le prix d'une");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerEngineer(model));
                    this.p2 = true;
                }else{
                    model.addPlayer(new PlayerEngineer(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (x >= 5 * MenuView.WIDTH / 12 && x <= 5 * MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p3) {
            if (count3 == 0) {
                count3++;
                count1 = 0;
                count2 = 0;
                count4 = 0;
                count5 = 0;
                count6 = 0;
                menu.description.setText("Peut donner une clé à un joueur distant");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerMessenger(model));
                    this.p3 = true;
                }else{
                    model.addPlayer(new PlayerMessenger(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (x >= 7 * MenuView.WIDTH / 12 && x <= 7 * MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p4) {
            if (count4 == 0) {
                count4++;
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count5 = 0;
                count6 = 0;
                menu.description.setText("Peut assécher une zone submergée");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerNautilus(model));
                    this.p4 = true;
                } else {
                    model.addPlayer(new PlayerNautilus(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (x >= 9 * MenuView.WIDTH / 12 && x <= 9 * MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p5) {
            if (count5 == 0) {
                count5++;
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count4 = 0;
                count6 = 0;
                menu.description.setText("En appuyant sur E, vous vous déplacerez sur une case safe aléatoire");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerPilote(model));
                    this.p5 = true;
                } else {
                    model.addPlayer(new PlayerPilote(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (x >= 11 * MenuView.WIDTH / 12 && x <= 11 * MenuView.WIDTH / 12 + 75 && y >= MenuView.HEIGHT / 2 && y <= MenuView.HEIGHT / 2 + 75 && !this.p6) {
            if (count6 == 0) {
                count6++;
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count4 = 0;
                count5 = 0;
                menu.description.setText("Peut traverser les zones submergées sans danger");
                Dimension s = menu.description.getPreferredSize();
                menu.description.setBounds(MenuView.WIDTH / 2 - s.width / 2, MenuView.HEIGHT / 2 + 200, s.width, s.height);
                menu.description.setVisible(true);
            } else {
                if (menu.count < 3) {
                    menu.count++;
                    menu.description.setVisible(false);
                    model.addPlayer(new PlayerDiver(model));
                    this.p6 = true;
                }else{
                    model.addPlayer(new PlayerDiver(model));
                    try {
                        View NewView = new View(model);
                        this.view.closeWindow();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
