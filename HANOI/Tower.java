import java.util.ArrayList;

/**
 * Created by Noah on 04.11.2016.
 */
public class Tower {

    ArrayList<Disc> discs = new ArrayList<>();

    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    public void setDiscs(ArrayList<Disc> discs) {
        this.discs = discs;
    }

    public Disc getDisc(int position) {
        return discs.get(position);
    }

    public void pushDisc (Disc disc) {
        discs.add(disc);
    }

}
