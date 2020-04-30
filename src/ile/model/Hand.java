package ile.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Key> key;
    private Model model;
    private int x;
    private int y;

    public Hand(Model m) {
        this.key = new ArrayList<>();
        this.model = m;
        this.x = 0;
        this.y = 0;
    }

    public boolean hasKey() {
        return (!this.key.isEmpty());
    }

    public ArrayList<Key> getKey() {
        return key;
    }

    public void addKey(Key k) {
        if(this.key.size() == 0) {
            this.key.add(k);
        }
    }

    public void setHand(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public int getHandX() {
        return this.x;
    }

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

    public boolean isNearby(Area a) {
        for (int i = -1 ; i <= 1 ; i++) {
            if ((this.x+i == a.getX() && this.y == a.getY()) || (this.x == a.getX() && this.y+i == a.getY())) {
                return true;
            }
        }
        return false;
    }

    public void removeKey(){
        if(this.key.size() == 1) {
            this.key.remove(0);
        }
    }

}
