
package saegraphmap.linkedlist;



import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class TListe {

    private TLieu liste;

    public TListe(String nom_Fichier) {
        ArrayList<String> listPts = new ArrayList<>();
        try {
            BufferedReader buffer_csv = new BufferedReader(new FileReader(nom_Fichier));
            while (buffer_csv.ready()) {
                listPts.add(buffer_csv.readLine());
            }
            buffer_csv.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur fichier non trouver: " + ex);
        } catch (IOException ex) {
            System.out.println("Erreur : " + ex);
        }

        int index = 0;
        for (String ligne : listPts) {
            String[] lieu = ligne.split(":", 2);
            this.ajoutLieu(new TLieu(lieu[0].split(",")[1], lieu[0].split(",")[0].charAt(0), null));
            listPts.set(index, lieu[1]);
            index++;
        }

        TLieu celluleLieu = this.liste;
        for (String ligne : listPts) {
            String[] routes = ligne.split(";");
            System.out.println(ligne);
            for (String route : routes) {
                char typeRoute = route.split("::")[0].split(",")[0].toCharArray()[0];
                float longueurRoute = Integer.parseInt(route.split("::")[0].split(",")[1]);
                String destination = route.split("::")[1].split(",")[1];
                celluleLieu.ajoutRoute(new TRoute(longueurRoute, typeRoute, celluleLieu, chercheLieu(destination)));
            }
            celluleLieu = celluleLieu.getSuivant();
        }
    }

    public TLieu getListe() {
        return liste;
    }

    public TLieu chercheLieu(String lieu) {
        TLieu elliste = this.liste;
        int i = 0;
        while (elliste != null) {
            if (elliste.getNomLieu().equals(lieu)) {
                return elliste;
            } else {
                elliste = elliste.getSuivant();
            }
        }
        return null;
    }
    
    public ArrayList<TRoute> chercheRoutes(String nomlieu1, String nomlieu2) {
        float distance = Float.MAX_VALUE;
        TLieu lieu1 = chercheLieu(nomlieu1);
        TLieu lieu2 = chercheLieu(nomlieu2);
        ArrayList<TRoute> routes = new ArrayList<TRoute>();
        for(TRoute route = lieu1.getTetelisteroutes(); route!=null ; route = route.getSuivant()){
            if(route.getLieuRejoint2() == lieu2) {
                if(route.getDistance() <  distance){
                    routes.add(route);
                    distance = route.getDistance();
                }
                else if(route.getDistance() ==  distance){
                    routes.add(route);
                }
            }
        }
        for(TRoute route = lieu2.getTetelisteroutes(); route!=null ; route = route.getSuivant()){
            if(route.getLieuRejoint2() == lieu1) {
                if(route.getDistance() <  distance){
                    routes.add(route);
                    distance = route.getDistance();
                }
                else if(route.getDistance() ==  distance){
                    routes.add(route);
                }
            }
        }
        return routes;
    }

    public ArrayList afficherVilles() {
        TLieu elliste = this.liste;
        ArrayList villes = new ArrayList();
        while (elliste != null) {
            if (elliste.getType() == 'V') {
                villes.add(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
        return villes;
    }

    public ArrayList afficherRestaurants() {
        TLieu elliste = this.liste;
        ArrayList resto = new ArrayList();
        while (elliste != null) {
            if (elliste.getType() == 'R') {
                resto.add(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
        return resto;
    }

    public ArrayList afficherLoisir() {
        TLieu elliste = this.liste;
        ArrayList loisirs = new ArrayList();
        while (elliste != null) {
            if (elliste.getType() == 'L') {
                loisirs.add(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
        return loisirs;
    }

    public void afficherDepartementales() {
        TLieu elliste = this.liste;
        System.out.println("liste des departementales :");
        while (elliste != null) {
            TRoute ellisteroute = this.liste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'D') {
                    System.out.println("departementale de " + ellisteroute.getLieuRejoint1() + " a " + ellisteroute.getLieuRejoint2());
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
    }

    public void afficherNationales() {
        TLieu elliste = this.liste;
        System.out.println("liste des nationales :");
        while (elliste != null) {
            TRoute ellisteroute = this.liste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'N') {
                    System.out.println("nationale de " + ellisteroute.getLieuRejoint1() + " a " + ellisteroute.getLieuRejoint2());
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
    }

    public void afficherAutoroute() {
        TLieu elliste = this.liste;
        System.out.println("liste des autoroutes :");
        while (elliste != null) {
            TRoute ellisteroute = this.liste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'A') {
                    System.out.println("autoroute de " + ellisteroute.getLieuRejoint1() + " a " + ellisteroute.getLieuRejoint2());
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
    }

    public int compterVilles() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            if (elliste.getType() == 'V') {
                nb++;
            }
            elliste = elliste.getSuivant();
        }
        return nb;
    }

    public int compterRestaurants() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            if (elliste.getType() == 'R') {
                nb++;
            }
            elliste = elliste.getSuivant();
        }
        return nb;
    }

    public int compterLoisir() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            if (elliste.getType() == 'L') {
                nb++;
            }
            elliste = elliste.getSuivant();
        }
        return nb;
    }

    public int compterDepartementales() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            TRoute ellisteroute = elliste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'D') {
                    nb++;
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
        return nb / 2;
    }

    public int compterNationales() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            TRoute ellisteroute = elliste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'N') {
                    nb++;
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
        return nb / 2;
    }

    public int compterAutoroute() {
        TLieu elliste = this.liste;
        int nb = 0;
        while (elliste != null) {
            TRoute ellisteroute = elliste.getTetelisteroutes();
            while (ellisteroute != null) {
                if (ellisteroute.getTypeRoute() == 'A') {
                    nb++;
                }
                ellisteroute = ellisteroute.getSuivant();
            }
            elliste = elliste.getSuivant();
        }
        return nb / 2;
    }

    public ArrayList<TLieu> unDistance(TLieu lieu1d, char typecherche) {
        ArrayList UnDistance = new ArrayList();
        for (TRoute route = lieu1d.getTetelisteroutes() ; route != null; route = route.getSuivant()){
            if(route.getLieuRejoint2().getType() == typecherche)
                UnDistance.add(route.getLieuRejoint2());
        }
        return UnDistance;
    }


    public boolean isUnDistance(TLieu lieu1, TLieu lieu2) {
        ArrayList<TLieu> listUnDistance = lieu1.unDistance();
        return listUnDistance.contains(lieu2);
    }

    public ArrayList deuxDistance(TLieu lieu2d, char typecherche) {
        ArrayList deuxDistance = new ArrayList();
        for (TRoute route = lieu2d.getTetelisteroutes() ; route != null; route = route.getSuivant()){
            for (TRoute route2 = route.getLieuRejoint2().getTetelisteroutes() ; route2 != null; route2 = route2.getSuivant()){
                if(!deuxDistance.contains(route2.getLieuRejoint2()) && route2.getLieuRejoint2()!= lieu2d && route.getLieuRejoint2().getType() == typecherche){
                    deuxDistance.add(route2.getLieuRejoint2());
            }
            }
        }
        return deuxDistance;
    }

    public ArrayList deuxDistance(TLieu lieu2d) {
        ArrayList deuxDistance = new ArrayList();
        for (TRoute route = lieu2d.getTetelisteroutes() ; route != null; route = route.getSuivant()){
            for (TRoute route2 = route.getLieuRejoint2().getTetelisteroutes() ; route2 != null; route2 = route2.getSuivant()){
                if(!deuxDistance.contains(route2.getLieuRejoint2())){
                    deuxDistance.add(route2.getLieuRejoint2());
                }
            }
        }
        return deuxDistance;
    }

    public boolean isDeuxDistance(TLieu lieu1, TLieu lieu2) {
        for(TRoute route = lieu1.getTetelisteroutes() ; route!=null ; route = route.getSuivant()){
            for(TRoute route2 = lieu2.getTetelisteroutes() ; route2!=null ; route2 = route2.getSuivant()){
                if(route.getLieuRejoint2() == route2.getLieuRejoint2()) return true;
            }
        }
        return false;
    }

    public String plusCulturelle(TLieu lieu1, TLieu lieu2) {
        Set<TLieu> lieuSet = new LinkedHashSet<>(deuxDistance(lieu1, 'L'));
        lieuSet.addAll(unDistance(lieu1, 'L'));
        int nblieu1 = lieuSet.size();

        lieuSet = new LinkedHashSet<>(deuxDistance(lieu2, 'L'));
        lieuSet.addAll(unDistance(lieu2, 'L'));
        int nblieu2 = lieuSet.size();

        if (nblieu1 == nblieu2) return(lieu1.getNomLieu() + " et " + lieu2.getNomLieu() + " sont autant culturelles");
        else if (nblieu1 > nblieu2) return(lieu1.getNomLieu() + " est plus culturelle que " + lieu2.getNomLieu());
        else return(lieu1.getNomLieu() + " est moins culturelle que " + lieu2.getNomLieu());
    }

    public String plusOuverte(TLieu lieu1, TLieu lieu2) {
        Set<TLieu> lieuSet = new LinkedHashSet<>(deuxDistance(lieu1));
        lieuSet.addAll(lieu1.unDistance());
        int nblieu1 = lieuSet.size();

        lieuSet = new LinkedHashSet<>(deuxDistance(lieu2));
        lieuSet.addAll(lieu2.unDistance());
        int nblieu2 = lieuSet.size();
        if (nblieu1 == nblieu2) return(lieu1.getNomLieu() + " et " + lieu2.getNomLieu() + " sont autant ouvertes");
        else if (nblieu1 > nblieu2) return(lieu1.getNomLieu() + " est plus ouvertes que " + lieu2.getNomLieu());
        else return(lieu1.getNomLieu() + " est moins ouvertes que " + lieu2.getNomLieu());
    }

    public String plusGastronomique(TLieu lieu1, TLieu lieu2) {
        Set<TLieu> lieuSet = new LinkedHashSet<>(deuxDistance(lieu1, 'R'));
        lieuSet.addAll(unDistance(lieu1, 'R'));
        int nblieu1 = lieuSet.size();

        lieuSet = new LinkedHashSet<>(deuxDistance(lieu2, 'R'));
        lieuSet.addAll(unDistance(lieu2, 'R'));
        int nblieu2 = lieuSet.size();
        if (nblieu1 == nblieu2) return(lieu1.getNomLieu() + " et " + lieu2.getNomLieu() + " sont autant gastronomiques");
        else if (nblieu1 > nblieu2) return(lieu1.getNomLieu() + " est plus gastronomiques que " + lieu2.getNomLieu());
        else return(lieu1.getNomLieu() + " est moins gastronomiques que " + lieu2.getNomLieu());
    }

    public void ajoutLieu(TLieu lieuAjoute) {
        TLieu celluleLieu = this.liste;
        if (celluleLieu == null) {
            this.liste = lieuAjoute;
        } else {
            while (celluleLieu.getSuivant() != null) {
                celluleLieu = celluleLieu.getSuivant();
            }
            celluleLieu.setSuivant(lieuAjoute);
        }
    }

    public void changeLieuVisibility(char c, boolean visible){
        for(TLieu lieu = this.getListe(); lieu != null ; lieu = lieu.getSuivant()){
            if(lieu.getType() == c || c=='A' ) lieu.getrJTogBtn().setVisible(visible);
        }
    }

    public int compterLieu(){
        return compterLoisir() + compterRestaurants() + compterVilles();
    }

    public int compterRoute(){return  compterAutoroute() + compterDepartementales() + compterNationales();}
}
