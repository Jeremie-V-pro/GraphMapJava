package saegraphmap.linkedlist;

import java.util.Objects;

public class TLieu {
    public String nomLieu;
    private char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLieu suivant;
    private TRoute tetelisteroutes;

    public TLieu(String nomLieu, char type, TRoute tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.tetelisteroutes = tetelisteroutes;
    }

    public TLieu() {
        this.nomLieu = null;
        this.type = 'z';
        this.suivant = null;
        this.tetelisteroutes = null;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public char getType() {
        return type;
    }

    public TLieu getSuivant() {
        return suivant;
    }

    public TRoute getTetelisteroutes() {
        return tetelisteroutes;
    }

     public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setSuivant(TLieu suivant) {
        this.suivant = suivant;
    }

    public void setTetelisteroutes(TRoute tetelisteroutes) {
        this.tetelisteroutes = tetelisteroutes;
    }

    public void ajoutRoute(TRoute routeAjoute){
        TRoute celluleRoute = this.tetelisteroutes;
        if (celluleRoute == null){
            this.tetelisteroutes = routeAjoute;
        }
        else{
            while (celluleRoute.getSuivant() != null){
                celluleRoute = celluleRoute.getSuivant();
            }
            celluleRoute.setSuivant(routeAjoute);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TLieu)) return false;
        TLieu tlieu = (TLieu) o;
        return type == tlieu.type && Objects.equals(nomLieu, tlieu.nomLieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomLieu, type);
    }
}



