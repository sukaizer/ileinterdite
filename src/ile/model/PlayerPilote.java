package ile.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerPilote extends Player {

    /**
     * Constructeur de classe PlayerPilote
     * @param model
     */
    public PlayerPilote(Model model) {
        super(model);
    }

    /**
     * Effectue le déplacement spécial
     */
    public void deplacementPilote() {
        if (this.energy > 0) {
            ArrayList<Area> a = this.model.nonSubmergedAreas();
            int n = ThreadLocalRandom.current().nextInt(0, a.size());
            this.x = a.get(n).getX();
            this.y = a.get(n).getY();
            this.energy--;
        }
    }

}
