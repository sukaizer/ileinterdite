package ile.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    //TODO
    //on s'en occupera plus tard
    protected int x;
    protected int y;
    protected ArrayList<Key> pocket;
    protected Key[] keyPosition;
    public int energy;
    protected final double PROBKEY = 0.20;
    protected Model model;

    public Player(Model model) {
        this.model = model;
        this.x = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.y = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.pocket = new ArrayList<>();
        this.energy = 3;
        this.keyPosition = new Key[4];

    }

    public void reset() {
        this.energy = 3;
    }

    public void gainEnergy() {
        this.energy++;
    }

    public void loseEnergy() {
        this.energy--;
    }

    public boolean hasEnergy() {
        return this.energy > 0;
    }


    public boolean endTour() {
        return this.energy == 0;
    }


    //mettre le compteur d'energy dans la fonction Action
    public void Deplacement(Direction d) {
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
                        //System.out.println("yes");
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
     *
     * @return
     */
    public Area getArea() {
        return this.model.getArea(this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void addKey() {
        float probK = ThreadLocalRandom.current().nextFloat();
        if (probK <= PROBKEY) {
            this.pocket.add(probKey());
        }
    }

    public Key probKey() {
        float probK = ThreadLocalRandom.current().nextFloat();
        if (probK >= 0.75) {
            return Key.Air;
        } else if (probK >= 0.5) {
            return Key.Earth;
        } else if (probK >= 0.25) {
            return Key.Fire;
        } else return Key.Water;
    }

    public void probArtifact() {                      //touhouhijacklol
        if (this.getArea().getType() == Type.Water) {
            if (this.pocket.contains(Key.Water) && !this.model.getArtifacts().contains(Key.Water))
                this.model.addArtifact(Key.Water);
        } else if (this.getArea().getType() == Type.Fire) {
            if (this.pocket.contains(Key.Fire) && !this.model.getArtifacts().contains(Key.Fire))
                this.model.addArtifact(Key.Fire);
        } else if (this.getArea().getType() == Type.Air) {
            if (this.pocket.contains(Key.Air) && !this.model.getArtifacts().contains(Key.Air))
                this.model.addArtifact(Key.Air);
        } else if (this.getArea().getType() == Type.Earth) {
            if (this.pocket.contains(Key.Earth) && !this.model.getArtifacts().contains(Key.Earth))
                this.model.addArtifact(Key.Earth);
        }
    }


    public boolean takeArtifact() {
        if (this.getArea().getType() == Type.Water) {
            return this.pocket.contains(Key.Water) && !this.model.getArtifacts().contains(Key.Water);
        } else if (this.getArea().getType() == Type.Fire) {
            return this.pocket.contains(Key.Fire) && !this.model.getArtifacts().contains(Key.Fire);
        } else if (this.getArea().getType() == Type.Air) {
            return this.pocket.contains(Key.Air) && !this.model.getArtifacts().contains(Key.Air);
        } else if (this.getArea().getType() == Type.Earth) {
            return this.pocket.contains(Key.Earth) && !this.model.getArtifacts().contains(Key.Earth);
        }
        return false;
    }

    public ArrayList<Key> getKey() {
        return this.pocket;
    }


    /*
    return the number of the same jey before this one for a character
    @key : the position of the key (always under the pocket size)
     */
    public int numberKeys(Key type) {
        int res = 0;
        for (int i = 0; i < this.pocket.size(); i++) {
            if (this.pocket.get(i) == type) {
                res++;
            }
        }
        return res;
    }

    /*
    return a [0;3] int indicating the position of a key to print in the Inventory
     */
    public ArrayList<Key> positionKey() {
        ArrayList<Key> po = new ArrayList<>();
        for (Key k : this.pocket) {
            if (!po.contains(k)) {
                po.add(k);
            }
        }
        return po;
    }

}
