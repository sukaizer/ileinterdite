package ile.model;
import ile.Observable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    //TODO
    //on s'en occupera plus tard
    private int x;
    private int y;
    private ArrayList<Key> pocket;
    public int energy;
    private final double PROBKEY = 0.05;
    private Model model;
    private boolean alive;

    public Player(Model model){
        this.model = model;
        this.x = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.y = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.pocket = new ArrayList<>();
        this.energy = 3;
        this.alive = true;
    }

    public void reset() {
        this.energy = 3;
    }

    public void gainEnergy() { this.energy++;}

    public void loseEnergy() { this.energy--;}

    public boolean hasEnergy() { return this.energy > 0;}


    public boolean endTour(){
        return this.energy == 0;
    }


    //mettre le compteur d'energy dans la fonction Action
    public void Deplacement(Direction d){
        if (this.energy > 0) {
            switch (d) {
                case UP:
                    if (this.y > 0) {
                        this.y--;
                        this.energy--;
                    }
                    break;
                case DOWN:
                    if (this.y < Model.LONGUEUR - 1) {
                        this.y++;
                        this.energy--;
                    }
                    break;
                case RIGHT:
                    if (this.x < Model.LONGUEUR - 1) {
                        this.x++;
                        this.energy--;
                    }
                    break;
                case LEFT:
                    if (this.x > 0) {
                        this.x--;
                        this.energy--;
                    }
                    break;
            }
        }
    }

    /**
     * Renvoie une nouvelle zone sur laquelle se trouve le joueur
     * @return
     */
    public Area getArea(){
        return this.model.getArea(this.x,this.y);
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void addKey(){
        float probK = ThreadLocalRandom.current().nextFloat();
        if(probK >= PROBKEY){
            this.pocket.add(probKey());
        }
    }

    public Key probKey(){
        float probK = ThreadLocalRandom.current().nextFloat();
        if(probK >= 0.75){
            return Key.Air;
        }else if(probK >= 0.5){
            return Key.Earth;
        }else if(probK >= 0.25){
            return Key.Fire;
        }else return Key.Water;
    }

    public void probArtifact(){                      //touhouhijacklol
        if(this.getArea().getType() == Type.Water){
            if(this.pocket.contains(Key.Water) && !this.model.getArtifacts().contains(Key.Water)) this.model.addArtifact(Key.Water);
        } else if(this.getArea().getType() == Type.Fire){
            if(this.pocket.contains(Key.Fire) && !this.model.getArtifacts().contains(Key.Fire)) this.model.addArtifact(Key.Fire);
        } else if(this.getArea().getType() == Type.Air){
            if(this.pocket.contains(Key.Air) && !this.model.getArtifacts().contains(Key.Air)) this.model.addArtifact(Key.Air);
        } else if(this.getArea().getType() == Type.Earth){
            if(this.pocket.contains(Key.Earth) && !this.model.getArtifacts().contains(Key.Earth)) this.model.addArtifact(Key.Earth);
        }
    }

    public ArrayList<Key> getKey() {
        return this.pocket;
    }

    public void setDead(){
        this.alive = false;
    }
}
