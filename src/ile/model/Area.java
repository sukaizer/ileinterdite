package ile.model;

public class Area {
    private Type type;
    private State state;
    private final int x, y;

    public Area(Type type, int x, int y) {
        this.type = type;
        this.state = State.Normal;
        this.x = x;
        this.y = y;
    }


    public void changeType(Type type) {
        this.type = type;
    }

    public void unFloodState() {
        this.state = State.Normal;
    }

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

    public Type getType() {
        return this.type;
    }

    public State getState() {
        return this.state;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public int[] unflood() {
        int[] coord = new int[2];
        coord[0] = this.x;
        coord[1] = this.y;
        return coord;
    }

}
