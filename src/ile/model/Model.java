package ile.model;

import ile.Observable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Model extends Observable {
    public static final int LONGUEUR = 14; //pair uniquement peut etre faire un test
    private Area[][] areas;
    private ArrayList<Area> lands; //lands non submergées
    private ArrayList<Player> players;
    private Hand hand;
    private ArrayList<Key> artifacts;
    private int tour;

    public Model(){
        this.tour = 0;
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.players.add(new Player(this));
        this.players.add(new Player(this));
        this.players.add(new Player(this));
        this.players.add(new Player(this));
        this.hand = new Hand(this);
        this.areas = new Area[LONGUEUR][LONGUEUR];
        this.lands = new ArrayList<>();
        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < LONGUEUR ; j++) {
                areas[i][j] = new Area(Type.Land,i,j);
            }
        }


        /**
         * Nombres générés aléatoirement pour position des zones d'elements selon de grandes "zones"
         * On "sépare en 4" l'ile, peut-etre réajuster les nombres, à voir
         **/
        int element1X = ThreadLocalRandom.current().nextInt(0,LONGUEUR/2);
        int element1Y = ThreadLocalRandom.current().nextInt(0,LONGUEUR/2);

        int element2X = ThreadLocalRandom.current().nextInt(LONGUEUR/2,LONGUEUR);
        int element2Y= ThreadLocalRandom.current().nextInt(0,LONGUEUR/2);

        int element3X = ThreadLocalRandom.current().nextInt(0,LONGUEUR/2);
        int element3Y = ThreadLocalRandom.current().nextInt(LONGUEUR/2,LONGUEUR);

        int element4X = ThreadLocalRandom.current().nextInt(LONGUEUR/2,LONGUEUR);
        int element4Y = ThreadLocalRandom.current().nextInt(LONGUEUR/2,LONGUEUR);

        areas[element1X][element1Y].changeType(Type.Air);
        areas[element2X][element2Y].changeType(Type.Fire);
        areas[element3X][element3Y].changeType(Type.Water);
        areas[element4X][element4Y].changeType(Type.Earth);


        /**
         * L'héliport recouvre les 4 zones centrales de l'ile
         */
        areas[LONGUEUR/2 - 1][LONGUEUR/2 - 1].changeType(Type.Heliport);
        areas[LONGUEUR/2][LONGUEUR/2 - 1].changeType(Type.Heliport);
        areas[LONGUEUR/2 - 1][LONGUEUR/2].changeType(Type.Heliport);
        areas[LONGUEUR/2][LONGUEUR/2].changeType(Type.Heliport);

        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < LONGUEUR ; j++) {
                if(areas[i][j].getType().equals(Type.Land)){
                    this.lands.add(areas[i][j]);
                }
            }
        }

    }
    public Area getArea(int x, int y){
        return areas[x][y];
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getTour(){
        return this.tour;
    }

    public void nextTour(){
        if(this.tour < 3){
            this.tour++;
        }else{
            this.tour = 0;
        }
    }

    public void flooding(){
        for (int i = 0; i < 3; i++) {
            int n = ThreadLocalRandom.current().nextInt(0,lands.size());
            lands.get(n).floodState();
            if(lands.get(n).getState().equals(State.Submerged)){
                lands.remove(n);
            }
        }
    }

    public void unflooding(int x, int y) {
        if (this.areas[x][y].getState() == State.Flooded){
            this.areas[x][y].unFloodState();
        }
    }

    /**
     * Retourne les cases adjacentes.
     * @param a
     * @return
     */
    public Area[] getNearby(Area a){
        Area[] nearby;
        if(a.getY() == 0 && a.getX() == 0){
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() + 1];
        }else if(a.getY() == 0 && a.getX() == LONGUEUR - 1){
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() + 1];
        }else if(a.getY() == LONGUEUR - 1 && a.getX() == 0){
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
        }else if(a.getY() == LONGUEUR - 1 && a.getX() == LONGUEUR - 1){
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
        }else if(a.getX() == 0){
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        }else if(a.getX() == LONGUEUR - 1){
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        }else if(a.getY() == 0){
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        }else if(a.getY() == LONGUEUR - 1){
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() - 1];
        }else{
            nearby = new Area[5];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() - 1];
            nearby[4] = areas[a.getX()][a.getY() + 1];
        }
        return nearby;
    }


    public void addArtifact(Key key){
        this.artifacts.add(key);
    }

    public ArrayList<Key> getArtifacts() {
        return this.artifacts;
    }

}
