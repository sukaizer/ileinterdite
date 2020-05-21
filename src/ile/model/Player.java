package ile.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    protected int x;
    protected int y;
    protected ArrayList<Key> pocket;
    protected Key[] keyPosition;
    protected int energy;
    protected final double PROBKEY = 0.20;
    protected Model model;
    protected int nbMoves;
    protected int nbUnflooded;

    /**
     * Constructeur de classe Player
     * @param model le modèle lié au programme
     */
    public Player(Model model) {
        this.model = model;
        this.x = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.y = ThreadLocalRandom.current().nextInt(0, Model.LONGUEUR);
        this.pocket = new ArrayList<>();
        this.energy = 3;
        this.keyPosition = new Key[4];
        this.nbMoves = 0;
        this.nbUnflooded = 0;
    }

    /**
     * Redonne l'énergie du joueur,
     * qui permet d'effectuer tout type
     * d'action
     */
    public void reset() {
        this.energy = 3;
    }

    /**
     * Enlève 1 d'énergie au joueur
     */
    public void loseEnergy() {
        this.energy--;
    }

    /**
     * Teste si le joueur a encore de l'énergie
     * @return boolean
     */
    public boolean hasEnergy() {
        return this.energy > 0;
    }

    /**
     * Retourne l'énergie du joueur
     * @return int
     */
    public int getEnergy(){
        return this.energy;
    }

    /**
     * Retourne le nombre de déplacements effectués par le joueur
     * @return int
     */
    public int getNbMoves(){
        return this.nbMoves;
    }

    /**
     * Retourne le nombre de cases asséchées par le joueur
     * @return int
     */
    public int getNbUnflooded(){
        return this.nbUnflooded;
    }

    /**
     * Augmente le nombre de déplacements effectués
     */
    public void setNbMoves(){
        this.nbMoves++;
    }

    /**
     * Augmente le nombre de cases asséchées
     */
    public void setNbUnflooded(){
        this.nbUnflooded++;
    }

    /**
     * Deplacement du joueur
     * @param d direction du déplacement
     */
    public void move(Direction d) {
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
     *
     * @return Area
     */
    public Area getArea() {
        return this.model.getArea(this.x, this.y);
    }

    /**
     * Retourne la coordonnée x du joueur
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée y du joueur
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * Ajoute une clé à la poche du joueur (avec une proba
     * égale à PRBOKEY)
     */
    public void addKey() {
        float probK = ThreadLocalRandom.current().nextFloat();
        if (probK <= PROBKEY) {
            this.pocket.add(probKey());
        }
    }

    /**
     * Méthode permettant d'ajouter une clé d'un certain type
     * @return
     */
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

    /**
     * Ajoute l'artefact si le joueur possède la bonne clé et
     * s'il se trouve sur la bonne case
     */
    public void addArtifact() {
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

    /**
     * Teste si un joueur peut prendre un artefact
     * @return boolean
     */
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

    /**
     * Retourne la poche, càd la liste de clés du joueur
     * @return ArrayList<Key>
     */
    public ArrayList<Key> getKey() {
        return this.pocket;
    }


    /**
     * Retourne le nombre de clés que le joueur possède
     * @return int : position de la clé
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

    /**
     * Retourne la position de la clé
     * @return ArrayLisy<Key>
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
