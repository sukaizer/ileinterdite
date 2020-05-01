package ile.model;

public class PlayerIngenieur extends Player {
    private int flood;

    /**
     * Constructeur de classe PlayerIngenieur
     * @param model
     */
    public PlayerIngenieur(Model model) {
        super(model);
        this.flood = 0;
    }

    /**
     * Met l'attribut flood à 0
     */
    public void setFlood() {
        this.flood = 0;
    }

    /**
     * Augmente de 1 flood
     */
    public void floodPlus() {
        this.flood++;
    }

    /**
     * Retourne flood
     * @return int
     */
    public int getFlood() {
        return this.flood;
    }

}
