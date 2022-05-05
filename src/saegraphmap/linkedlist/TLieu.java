public class TLIEU {
    private String nomLieu;
    private char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLIEU precedent;
    private TLIEU suivant;
    private TROUTE tetelisteroutes;

    public TLIEU(String nomLieu, char type, TLIEU suivant, TLIEU precedent, TROUTE tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.suivant = suivant;
        this.precedent = precedent;
        this.tetelisteroutes = tetelisteroutes;
    }

    public TLIEU() {
        this.nomLieu = null;
        this.type = 'z';
        this.suivant = null;
        this.precedent = null;
        this.tetelisteroutes = null;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public char getType() {
        return type;
    }

    public TLIEU getPrecedent() {
        return precedent;
    }

    public TLIEU getSuivant() {
        return suivant;
    }

    public TROUTE getTetelisteroutes() {
        return tetelisteroutes;
    }


}
