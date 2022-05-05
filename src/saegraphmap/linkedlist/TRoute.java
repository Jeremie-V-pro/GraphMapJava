/**
 *
 * @author p2105876
 */
public class TROUTE {
    private int distance;
    private char typeRoute;//N (nationnale), D (departementale) ou A (autoroute)
    private TLIEU lieuRejoint1;
    private TLIEU lieuRejoint2;
    private TROUTE suivant;

    public TROUTE(int distance, char typeRoute, TLIEU lieuRejoint1, TLIEU lieuRejoint2, TROUTE suivant) {
        this.distance = distance;
        this.typeRoute = typeRoute;
        this.lieuRejoint1 = lieuRejoint1;
        this.lieuRejoint2 = lieuRejoint2;
        this.suivant = suivant;
    }

    public int getDistance() {
        return distance;
    }

    public char getTypeRoute() {
        return typeRoute;
    }

    public TLIEU getLieuRejoint1() {
        return lieuRejoint1;
    }

    public TLIEU getLieuRejoint2() {
        return lieuRejoint2;
    }

    public TROUTE getSuivant() {
        return suivant;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTypeRoute(char typeRoute) {
        this.typeRoute = typeRoute;
    }

    public void setLieuRejoint1(TLIEU lieuRejoint1) {
        this.lieuRejoint1 = lieuRejoint1;
    }

    public void setLieuRejoint2(TLIEU lieuRejoint2) {
        this.lieuRejoint2 = lieuRejoint2;
    }

    public void setSuivant(TROUTE suivant) {
        this.suivant = suivant;
    }

}
