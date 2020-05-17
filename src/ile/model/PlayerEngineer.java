package ile.model;

public class PlayerEngineer extends Player {
    private int flood;

    /**
     * Constructeur de classe PlayerEngineer
     * @param model
     */
    public PlayerEngineer(Model model) {
        super(model);
        this.flood = 0;
    }

    /**
     * Met l'attribut flood à 0
     */
    public void setFlood(){
        this.flood = 0;
    }

    /**
     * Augmente de 1 flood
     */
    public void floodPlus(){
        this.flood++;
    }

    /**
     * Retourne flood
     * @return int
     */
    public int getFlood(){
        return this.flood;
    }

}
