package saegraphmap.linkedlist;

/**
 *
 * @author p2105876
 */
public class TRoute {
    private final int distance;
    private final char typeRoute;//N (nationnale), D (departementale) ou A (autoroute)
    private final TLieu lieuRejoint1;
    private final TLieu lieuRejoint2;
    private TRoute suivant;

    private boolean routePluscourChemin = false;

    public TRoute(int distance, char typeRoute, TLieu lieuRejoint1, TLieu lieuRejoint2) {
        this.distance = distance;
        this.typeRoute = typeRoute;
        this.lieuRejoint1 = lieuRejoint1;
        this.lieuRejoint2 = lieuRejoint2;
    }

    public int getDistance() {
        return distance;
    }

    public char getTypeRoute() {
        return typeRoute;
    }

    public TLieu getLieuRejoint1() {
        return lieuRejoint1;
    }

    public TLieu getLieuRejoint2() {
        return lieuRejoint2;
    }

    public TRoute getSuivant() {
        return suivant;
    }

    public void setSuivant(TRoute suivant) {
        this.suivant = suivant;
    }

    public boolean isRoutePluscourChemin() {
        return routePluscourChemin;
    }

    public void setRoutePluscourChemin(boolean routePluscourChemin) {
        this.routePluscourChemin = routePluscourChemin;
    }

    @Override
    public String toString() {
        return "TRoute {" +
                "distance=" + distance +
                ", typeRoute=" + typeRoute +
                ", lieuRejoint1=" + lieuRejoint1.getNomLieu() +
                ", lieuRejoint2=" + lieuRejoint2 .getNomLieu()+
                ", suivant=" + suivant +
                '}';
    }
}
