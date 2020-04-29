package ile.view;

import ile.Observer;
import ile.model.Area;
import ile.model.Model;
import ile.model.Player;
import ile.model.State;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class InfoView extends JPanel implements Observer {
    private Model model;
    private JLabel actionCount;
    private JLabel floodIndication;
    private JLabel artifactIndication;
    private boolean b = false;
    private boolean b2 = false;

    public InfoView(Model model) {
        this.model = model;
        this.floodIndication = new JLabel("Vous pouvez cliquer sur une case à assécher");
        this.add(this.floodIndication);
        this.floodIndication.setVisible(b);
        this.floodIndication.setForeground(new Color(125,0,255));

        this.actionCount = new JLabel("Nombre d'actions restantes : " + 3);
        this.add(this.actionCount);

        this.artifactIndication = new JLabel("Appuyez sur A pour récupérer l'artefact");
        this.add(this.artifactIndication);
        this.artifactIndication.setVisible(b2);
        this.artifactIndication.setForeground(new Color(255,0, 47));
    }

    @Override
    public void paintComponent(Graphics g){
        this.b = false;
        this.b2 = false;
        this.actionCount.setText("Nombre d'actions restantes : " + this.model.getPlayers().get(this.model.getTour()).energy);
        Area[] nearby = this.model.getNearby(this.model.getPlayers().get(this.model.getTour()).getArea()); //les cases a coté du joueur dont c'est le tour
        for (Area area : nearby) {
            if (area.getState().equals(State.Flooded) && this.model.getPlayers().get(this.model.getTour()).hasEnergy()) {
                this.b = true;
            }
        }
        for (Player player : this.model.getPlayers()){
            if(player.takeArtifact()){
                this.b2 = true;
            }
        }
        this.floodIndication.setVisible(b);
        this.artifactIndication.setVisible(b2);
    }


    @Override
    public void update() {
        repaint();
    }
}
