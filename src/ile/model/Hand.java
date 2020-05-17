package ile.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Key> key;
    private Model model;
    private int x;
    private int y;
    private boolean flying;

    /**
     * Constructeur de la Hand, quand un objet d'un inventaire est pris, celui-ci est momentanément déplacé dans cette classe
     * @param m le modèle lié au programme
     */
    public Hand(Model m) {
        this.key = new ArrayList<>();
        this.model = m;
        this.x = 0;
        this.y = 0;
        this.flying = false;
    }

    /**
     * Vérifie s'il y a une clé dans la main
     * @return bool
     */
    public boolean hasKey() {
        return (!this.key.isEmpty());
    }

    /**
     * getter de la clé éventuelle de la main
     * @return ArrayList<Key>
     */
    public ArrayList<Key> getKey() {
        return key;
    }

    /**
     * Rajoute la clé k dans la main si celle-ci est vide
     * @param k la clé à ajouter
     */
    public void addKey(Key k) {
        if(this.key.size() == 0) {
            this.key.add(k);
        }
    }

    /**
     * place les coordonnées de la clé en (a, b)
     * @param a coordonnée x à modifier
     * @param b coordonnées y à mødifier
     */
    public void setHand(int a, int b) {
        this.x = a;
        this.y = b;
    }

    /**
     * getter de la coordonnées x de la main
     * @return int
     */
    public int getHandX() {
        return this.x;
    }

    /**
     * getter de la coordonnées y de la main
     * @return int
     */
    public int getHandY() {
        return this.y;
    }

    /**
     * Renvoie une nouvelle zone sur laquelle se trouve le joueur
     * @return
     */
    public Area getArea(){
        return this.model.getArea(this.x,this.y);
    }

    /**
     * Renvoie si oui ou non la zone en paramètre se trouve près de la main
     * @param a
     * @return
     */
    public boolean isNearby(Area a) {
        for (int i = -1 ; i <= 1 ; i++) {
            if ((this.x+i == a.getX() && this.y == a.getY()) || (this.x == a.getX() && this.y+i == a.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retire les clés de la main si elle en possède
     */
    public void removeKey(){
        if(this.key.size() == 1) {
            this.key.remove(0);
        }
    }

    /**
     * getter du booleen flying utilisé si la clé a été prise de la poche du messsager
     * @param b
     */
    public void setFlying(boolean b) {
        this.flying = b;
    }

    /**
     * getter de l'attribut flying
     * @return bool
     */
    public boolean getFlying() {
        return this.flying;
    }

}
