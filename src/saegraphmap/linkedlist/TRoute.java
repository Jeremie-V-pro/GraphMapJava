package saegraphmap.linkedlist;

/**
 *
 * @author p2105876
 */
public class TRoute {
    private int distance;
    private char typeRoute;//N (nationnale), D (departementale) ou A (autoroute)
    private TLieu lieuRejoint1;
    private TLieu lieuRejoint2;
    private TRoute suivant;

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

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTypeRoute(char typeRoute) {
        this.typeRoute = typeRoute;
    }

    public void setLieuRejoint1(TLieu lieuRejoint1) {
        this.lieuRejoint1 = lieuRejoint1;
    }

    public void setLieuRejoint2(TLieu lieuRejoint2) {
        this.lieuRejoint2 = lieuRejoint2;
    }

    public void setSuivant(TRoute suivant) {
        this.suivant = suivant;
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
