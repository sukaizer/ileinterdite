package ile.model;

public class PlayerExplorateur extends Player {
    public PlayerExplorateur(Model model) {
        super(model);
    }

    @Override
    public void Deplacement(Direction d) {
        if (this.energy > 0) {
            switch (d) {
                case UP:
                    if (this.y > 0) {
                        this.y--;
                        this.energy--;
                    }
                    break;
                case DOWN:
                    if (this.y < Model.LONGUEUR - 1) {
                        this.y++;
                        this.energy--;
                    }
                    break;
                case RIGHT:
                    if (this.x < Model.LONGUEUR - 1) {
                        this.x++;
                        this.energy--;
                    }
                    break;
                case LEFT:
                    if (this.x > 0) {
                        this.x--;
                        this.energy--;
                    }
                    break;
                case UP_LEFT:
                    if (this.y > 0 && this.x > 0) {
                        this.y--;
                        this.x--;
                        this.energy--;
                    }
                    break;
                case UP_RIGHT:
                    if (this.y > 0 && this.x < Model.LONGUEUR - 1) {
                        this.y--;
                        this.x++;
                        this.energy--;
                    }
                    break;
                case DOWN_LEFT:
                    if (this.x > 0 && this.y < Model.LONGUEUR - 1) {
                        this.x--;
                        this.y++;
                        this.energy--;
                    }
                    break;
                case DOWN_RIGHT:
                    if (this.x < Model.LONGUEUR && this.y < Model.LONGUEUR - 1) {
                        this.x++;
                        this.y++;
                        this.energy--;
                    }
                    break;
            }
        }
    }
}
