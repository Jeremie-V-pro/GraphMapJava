package saegraphmap.linkedlist;

public class TLieu {
    private String nomLieu;
    private char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLieu suivant;
    private TRoute tetelisteroutes;

    public TLieu(String nomLieu, char type, TLieu suivant, TLieu precedent, TRoute tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.suivant = suivant;
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


}
