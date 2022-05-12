
package saegraphmap.linkedlist;



import java.io.*;
import java.util.ArrayList;

public class TListe {

    private TLieu liste;

    public TListe() {
        this.liste = null;
    }

    public TListe(String nom_Fichier) {
        ArrayList<String> listpts = new ArrayList<>();
        try {
            BufferedReader buffer_csv = new BufferedReader(new FileReader(nom_Fichier));
            while (buffer_csv.ready()) {
                listpts.add(buffer_csv.readLine());
            }
            buffer_csv.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur fichier non trouver: " + ex);
        } catch (IOException ex) {
            System.out.println("Erreur : " + ex);
        }

        int index = 0;
        for (String ligne : listpts) {
            String[] lieu = ligne.split(":", 2);
            this.ajoutLieu(new TLieu(lieu[0].split(",")[1], lieu[0].split(",")[0].charAt(0), null));
            listpts.set(index, lieu[1]);
            index++;
        }

        TLieu celluleLieu = this.liste;
        for (String ligne : listpts) {
            String[] routes = ligne.split(";");
            for (String route : routes) {
                char typeRoute = route.split("::")[0].split(",")[0].toCharArray()[0];
                int longueurRoute = Integer.parseInt(route.split("::")[0].split(",")[1]);
                String destination = route.split("::")[1].split(",")[1];
                celluleLieu.ajoutRoute(new TRoute(longueurRoute, typeRoute, celluleLieu, chercheLieu(destination)));
            }
            System.out.println(celluleLieu.getTetelisteroutes().toString());
            celluleLieu = celluleLieu.getSuivant();
        }

    }


    public TLieu chercheLieu(String lieu) {
        TLieu elliste = this.liste;
        while (elliste != null) {
            if (elliste.getNomLieu().equals(lieu)) {
                return elliste;
            } else {
                elliste = elliste.getSuivant();
            }
        }
        return null;
    }

    public void afficherVilles() {
        TLieu elliste = this.liste;
        System.out.println("liste des villes :");
        while (elliste != null) {
            if (elliste.getType() == 'V') {
                System.out.println(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
    }

    public void afficherRestaurants() {
        TLieu elliste = this.liste;
        System.out.println("liste des restaurants :");
        while (elliste != null) {
            if (elliste.getType() == 'R') {
                System.out.println(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
    }

    public void afficherLoisir() {
        TLieu elliste = this.liste;
        System.out.println("liste des lieux de loisir :");
        while (elliste != null) {
            if (elliste.getType() == 'L') {
                System.out.println(elliste.getNomLieu());
            }
            elliste = elliste.getSuivant();
        }
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
            TRoute ellisteroute = this.liste.getTetelisteroutes();
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
            TRoute ellisteroute = this.liste.getTetelisteroutes();
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
            TRoute ellisteroute = this.liste.getTetelisteroutes();
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

    public void unDistance(String Lieu, char typecherche) {
        TLieu elliste = this.liste;
        while (elliste != null) {
            if (elliste.getNomLieu().equals(Lieu)) {
                TRoute ellisteroute = this.liste.getTetelisteroutes();
                while (ellisteroute != null) {
                    if (ellisteroute.getLieuRejoint2().getType() == typecherche) {
                        System.out.println(ellisteroute.getLieuRejoint2());
                    }
                    ellisteroute = ellisteroute.getSuivant();
                }
                return;
            }
            elliste = elliste.getSuivant();
        }
    }

    public void unDistance(String Lieu) {
        TLieu elliste = this.liste;
        while (elliste != null) {
            if (elliste.getNomLieu().equals(Lieu)) {
                TRoute ellisteroute = this.liste.getTetelisteroutes();
                while (ellisteroute != null) {
                    System.out.println(ellisteroute.getLieuRejoint2());
                    ellisteroute = ellisteroute.getSuivant();
                }
                return;
            }
            elliste = elliste.getSuivant();
        }
    }

    public ArrayList deuxDistance(String Lieu, char typecherche) {
        TLieu elliste = this.liste;
        ArrayList deuxDistance = new ArrayList();
        while (elliste != null) {
            if (elliste.getNomLieu().equals(Lieu)) {
                TRoute ellisteroute = this.liste.getTetelisteroutes();
                while (ellisteroute != null) {
                    TRoute ellistedeuxdist = ellisteroute.getLieuRejoint2().getTetelisteroutes();
                    while (ellistedeuxdist != null) {
                        if (ellistedeuxdist.getLieuRejoint2().getType() == typecherche) {
                            if (!(deuxDistance.contains(ellistedeuxdist.getLieuRejoint2()))) {
                                deuxDistance.add(ellistedeuxdist.getLieuRejoint2());
                                System.out.println(ellistedeuxdist.getLieuRejoint2());
                            }
                        }
                        ellistedeuxdist = ellistedeuxdist.getSuivant();
                    }
                    ellisteroute = ellisteroute.getSuivant();
                }
                return deuxDistance;
            }
            elliste = elliste.getSuivant();
        }
        return null;
    }

    public ArrayList deuxDistance(String Lieu) {
        TLieu elliste = this.liste;
        ArrayList deuxDistance = new ArrayList();
        while (elliste != null) {
            if (elliste.getNomLieu().equals(Lieu)) {
                TRoute ellisteroute = this.liste.getTetelisteroutes();
                while (ellisteroute != null) {
                    TRoute ellistedeuxdist = ellisteroute.getLieuRejoint2().getTetelisteroutes();
                    while (ellistedeuxdist != null) {
                        if (!(deuxDistance.contains(ellistedeuxdist.getLieuRejoint2()))) {
                            deuxDistance.add(ellistedeuxdist.getLieuRejoint2());
                            System.out.println(ellistedeuxdist.getLieuRejoint2());
                        }
                        ellistedeuxdist = ellistedeuxdist.getSuivant();
                    }
                    ellisteroute = ellisteroute.getSuivant();
                }
                return deuxDistance;
            }
            elliste = elliste.getSuivant();
        }
        return null;
    }

    public boolean isDeuxDistance(String lieu1, String lieu2) {
        TLieu tlieu1 = new TLieu();
        tlieu1 = chercheLieu(lieu1);
        TLieu tlieu2 = new TLieu();
        tlieu2 = chercheLieu(lieu1);
        TRoute elliste1 = tlieu1.getTetelisteroutes();
        while (elliste1 != null) {
            TRoute elliste2 = tlieu2.getTetelisteroutes();
            while (elliste2 != null) {
                if (elliste1.getLieuRejoint2().equals(elliste2.getLieuRejoint2())) {
                    return true;
                }
                elliste2 = elliste2.getSuivant();
            }
            elliste1 = elliste1.getSuivant();
        }
        return false;
    }

    public void plusCulturelle(String lieu1, String lieu2) {
        int nblieu1;
        nblieu1 = deuxDistance(lieu1, 'L').size();
        int nblieu2;
        nblieu2 = deuxDistance(lieu2, 'L').size();
        if (nblieu1 == nblieu2) System.out.println(lieu1 + " et " + lieu2 + " sont autant culturelles");
        else if (nblieu1 > nblieu2) System.out.println(lieu1 + " est plus culturelle que " + lieu2);
        else System.out.println(lieu1 + " est moins culturelle que " + lieu2);
    }

    public void plusOuverte(String lieu1, String lieu2) {
        int nblieu1;
        nblieu1 = deuxDistance(lieu1).size();
        int nblieu2;
        nblieu2 = deuxDistance(lieu2).size();
        if (nblieu1 == nblieu2) System.out.println(lieu1 + " et " + lieu2 + " sont autant ouvertes");
        else if (nblieu1 > nblieu2) System.out.println(lieu1 + " est plus ouvertes que " + lieu2);
        else System.out.println(lieu1 + " est moins ouvertes que " + lieu2);
    }

    public void plusGastronomique(String lieu1, String lieu2) {
        int nblieu1;
        nblieu1 = deuxDistance(lieu1, 'R').size();
        int nblieu2;
        nblieu2 = deuxDistance(lieu2, 'R').size();
        if (nblieu1 == nblieu2) System.out.println(lieu1 + " et " + lieu2 + " sont autant gastronomiques");
        else if (nblieu1 > nblieu2) System.out.println(lieu1 + " est plus gastronomiques que " + lieu2);
        else System.out.println(lieu1 + " est moins gastronomiques que " + lieu2);
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

}
