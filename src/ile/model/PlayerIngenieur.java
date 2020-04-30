package ile.model;

public class PlayerIngenieur extends Player {
    private int flood;

    public PlayerIngenieur(Model model) {
        super(model);
        this.flood = 0;
    }

    public void setFlood(){
        this.flood = 0;
    }

    public void floodPlus(){
        this.flood++;
    }

    public int getFlood(){
        return this.flood;
    }

}
