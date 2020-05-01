package ile.model;

public class Area {
    private Type type;
    private State state;
    private final int x, y;

    /**
     * Constructeur de classe Area
     * @param type type de la case
     * @param x coordonnée x de la case
     * @param y coordonnée y de la case
     */
    public Area(Type type, int x, int y) {
        this.type = type;
        this.state = State.Normal;
        this.x = x;
        this.y = y;
    }

    /**
     * Change le type de la case
     * @param type le type qu'on veut attribuer
     */
    public void changeType(Type type) {
        this.type = type;
    }

    /**
     * Assèche la case, lui donne l'état normal
     */
    public void unFloodState() {
        this.state = State.Normal;
    }

    /**
     * "Inonde" une case, si normale alors deviens inondée,
     * si inondée devient submergée
     */
    public void floodState() {
        switch (this.state) {
            case Normal:
                this.state = State.Flooded;
                break;
            case Flooded:
                this.state = State.Submerged;
                break;
        }
    }

    /**
     * Retourne le type de la case
     * @return type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Retourne l'état de la case
     * @return state
     */
    public State getState() {
        return this.state;
    }

    /**
     * Retourne la coordonnée x
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée y
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * Retourne un tableau de taille 2
     * comprenant les coordonnées de la case
     * @return int[]
     */
    public int[] unflood() {
        int[] coord = new int[2];
        coord[0] = this.x;
        coord[1] = this.y;
        return coord;
    }

}
