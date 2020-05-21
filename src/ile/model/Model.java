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

    /**
     * Constructeur de classe Model
     */

    public Model(){
        this.tour = 0;
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();

        this.hand = new Hand(this);

        this.areas = new Area[LONGUEUR][LONGUEUR];
        this.lands = new ArrayList<>();
        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                areas[i][j] = new Area(Type.Land, i, j);
            }
        }


        /**
         * Nombres générés aléatoirement pour position des zones d'elements selon de grandes "zones"
         * On "sépare en 4" l'ile, peut-etre réajuster les nombres, à voir
         **/
        int element1X = ThreadLocalRandom.current().nextInt(0, LONGUEUR / 2);
        int element1Y = ThreadLocalRandom.current().nextInt(0, LONGUEUR / 2);

        int element2X = ThreadLocalRandom.current().nextInt(LONGUEUR / 2, LONGUEUR);
        int element2Y = ThreadLocalRandom.current().nextInt(0, LONGUEUR / 2);

        int element3X = ThreadLocalRandom.current().nextInt(0, LONGUEUR / 2);
        int element3Y = ThreadLocalRandom.current().nextInt(LONGUEUR / 2, LONGUEUR);

        int element4X = ThreadLocalRandom.current().nextInt(LONGUEUR / 2, LONGUEUR);
        int element4Y = ThreadLocalRandom.current().nextInt(LONGUEUR / 2, LONGUEUR);

        areas[element1X][element1Y].changeType(Type.Air);
        areas[element2X][element2Y].changeType(Type.Fire);
        areas[element3X][element3Y].changeType(Type.Water);
        areas[element4X][element4Y].changeType(Type.Earth);


        /**
         * L'héliport recouvre les 4 zones centrales de l'ile
         */
        areas[LONGUEUR / 2 - 1][LONGUEUR / 2 - 1].changeType(Type.Heliport);
        areas[LONGUEUR / 2][LONGUEUR / 2 - 1].changeType(Type.Heliport);
        areas[LONGUEUR / 2 - 1][LONGUEUR / 2].changeType(Type.Heliport);
        areas[LONGUEUR / 2][LONGUEUR / 2].changeType(Type.Heliport);

        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                if (areas[i][j].getType().equals(Type.Land)) {
                    this.lands.add(areas[i][j]);
                }
            }
        }

    }

    /**
     * Ajoute un joueur donné aux joueurs présents
     * @param player
     */
    public void addPlayer(Player player){
        this.players.add(player);
    }

    /**
     * Renvoie la case selon les coordonnées x et y voulues
     * @param x coord x
     * @param y coord y
     * @return Area
     */
    public Area getArea(int x, int y){
        return areas[x][y];
    }

    /**
     * Retourne la liste comprenant les joueurs
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    /**
     * Retourne la main associée au modèle
     * @return Hand
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Retourne le tour auquel le jeu en est
     * @return int
     */
    public int getTour(){
        return this.tour;
    }

    /**
     * Passe au prochain tour
     */
    public void nextTour(){
        if(this.tour < 3){
            this.tour++;
        } else {
            this.tour = 0;
        }
    }

    /**
     * Inonde 3 cases aléatoires
     */
    public void flooding(){
        for (int i = 0; i < 3; i++) {
            int n = ThreadLocalRandom.current().nextInt(0, lands.size());
            lands.get(n).floodState();
            if (lands.get(n).getState().equals(State.Submerged)) {
                lands.remove(n);
            }
        }
    }

    /**
     * Assèche une case, si possible
     * @param x coord x de la case à assécher
     * @param y coord y de la case à assécher
     * @return boolean
     */
    public boolean unflooding(int x, int y) {
        if (this.areas[x][y].getState() == State.Flooded) {
            this.areas[x][y].unFloodState();
            return true;
        }
        return false;
    }

    /**
     * Assèche une case, si possible (version PlayerNautilus)
     * @param x coord x de la case à assécher
     * @param y coord y de la case à assécher
     * @param b ignoré.
     * @return boolean
     */
    public boolean unflooding(int x, int y, boolean b){
        if (this.areas[x][y].getState() == State.Flooded || this.areas[x][y].getState() == State.Submerged){
            this.areas[x][y].unFloodState();
            return true;
        }
        return false;
    }

    /**
     * Retourne les cases adjacentes.
     *
     * @param a la case de base
     * @return
     */
    public Area[] getNearby(Area a) {
        Area[] nearby;
        if (a.getY() == 0 && a.getX() == 0) {
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() + 1];
        } else if (a.getY() == 0 && a.getX() == LONGUEUR - 1) {
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() + 1];
        } else if (a.getY() == LONGUEUR - 1 && a.getX() == 0) {
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
        } else if (a.getY() == LONGUEUR - 1 && a.getX() == LONGUEUR - 1) {
            nearby = new Area[3];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
        } else if (a.getX() == 0) {
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() + 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        } else if (a.getX() == LONGUEUR - 1) {
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX()][a.getY() - 1];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        } else if (a.getY() == 0) {
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() + 1];
        } else if (a.getY() == LONGUEUR - 1) {
            nearby = new Area[4];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() - 1];
        } else {
            nearby = new Area[5];
            nearby[0] = a;
            nearby[1] = areas[a.getX() - 1][a.getY()];
            nearby[2] = areas[a.getX() + 1][a.getY()];
            nearby[3] = areas[a.getX()][a.getY() - 1];
            nearby[4] = areas[a.getX()][a.getY() + 1];
        }
        return nearby;
    }


    /**
     * Retourne les cases autour du joueur
     *
     * @param a joueur de type PlayerExplorateur
     * @return ArrayList<Area>
     */
    public ArrayList<Area> getNearby(PlayerExplorator a){
        ArrayList<Area> nearby = new ArrayList<>();
        for (int i = a.getX() - 1; i < a.getX() + 2; i++) {
            for (int j = a.getY() - 1; j < a.getY() + 2; j++) {
                if (i > -1 && j > -1 && i < LONGUEUR && j < LONGUEUR) {
                    nearby.add(areas[i][j]);
                }
            }
        }
        return nearby;
    }

    /**
     * Ajoute un artefact à la liste d'artefacts
     * @param key artefact de type Key
     */
    public void addArtifact(Key key){
        this.artifacts.add(key);
    }

    /**
     * Retourne la liste d'artefacts
     * @return ArrayList<Key>
     */
    public ArrayList<Key> getArtifacts() {
        return this.artifacts;
    }

    /**
     * Teste si la partie est gagnée,
     * retourne vrai si gagnée
     * @return boolean
     */
    public boolean testWin(){
        int n = 0;
        if (this.artifacts.size() == 4) {
            for (Player player : this.players) {
                if (player.getArea().getType() == Type.Heliport) {
                    n++;
                }
            }
            if (n == this.players.size()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Teste si la partie est perdue,
     * retourne vrai si perdue
     * @return boolean
     */
    public boolean testLoose() {
        for (Player player : this.players) {
            if(player.getArea().getState() == State.Submerged && !(player instanceof PlayerDiver)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la liste de cases non submergées
     * @return ArrayList<Area>
     */
    public ArrayList<Area> unSubmergedAreas(){
        return this.lands;
    }

    /**
     * Effectue le déplacement spécial du joueur
     * @param p le joueur de type PlayerPilote
     */
    public void deplacementPilote(PlayerPilote p){
        p.movePilote();
    }

    /**
     * Effectue l'asséchement (version spéciale)
     * @param p joueur de type PlayerIngenieur
     */
    public void unflooding(PlayerEngineer p){
        p.floodPlus();
    }

    /**
     * Retourne la valeur flood du joueur
     * @param p joueur de type PlayerIngenieur
     * @return int
     */
    public int getFlood(PlayerEngineer p){
        return p.getFlood();
    }

    /**
     * Remet à zéro la valeur flood du joueur
     * @param p joueur de type PlayerIngenieur
     */
    public void resetFlood(PlayerEngineer p){
        p.setFlood();
    }

}
