package ile.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerPilote extends Player {

    public PlayerPilote(Model model) {
        super(model);
    }

    public void deplacementPilote(){
        ArrayList<Area> a = this.model.nonSubmergedAreas();
        int n = ThreadLocalRandom.current().nextInt(0,a.size());
        this.x = a.get(n).getX();
        this.y = a.get(n).getY();
    }

}
