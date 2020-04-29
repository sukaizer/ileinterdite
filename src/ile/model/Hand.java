package ile.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Key> key;

    public Hand() {
        this.key = new ArrayList<>();
    }

    public boolean hasKey() {
        return (!this.key.isEmpty());
    }

    public ArrayList<Key> getKey() {
        return key;
    }

    public void addKey(Key k) {
        if(this.key.size() == 0) {
            this.key.add(k);
        }
    }

    public void removeKey(){
        if(this.key.size() == 1) {
            this.key.remove(0);
        }
    }

}
