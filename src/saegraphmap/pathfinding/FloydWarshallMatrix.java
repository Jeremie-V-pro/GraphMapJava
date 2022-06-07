package saegraphmap.pathfinding;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TListe;
import saegraphmap.linkedlist.TRoute;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallMatrix {
    private ArrayList<ArrayList<Vertex>> matrix = new ArrayList<>();
    private final float INFINITE = Float.MAX_VALUE;

    private int size;

    public FloydWarshallMatrix(TListe liste){
        size = liste.compterLieu();
        float longueur = INFINITE;
        for(int i = 0; i <size ; i++){
            matrix.add(new ArrayList<Vertex>(size));
            System.out.println(i);
        }
        int i = 0;
        int j = 0;
        for(TLieu lieu1 = liste.getListe(); lieu1 != null; lieu1= lieu1.getSuivant()){
            j = 0;
            ArrayList<TRoute>listeRoute;
            for(TLieu lieu2 = liste.getListe(); lieu2 != null; lieu2=lieu2.getSuivant(), longueur = INFINITE){
                if(lieu1 == lieu2){
                    longueur = 0;
                    listeRoute = null;
                }
                else {
                    listeRoute = liste.chercheRoutes(lieu1.getNomLieu(),lieu2.getNomLieu());
                    for (TRoute route: listeRoute) {
                        if(route.getDistance()<longueur) longueur=route.getDistance();
                    }
                    for (TRoute route: listeRoute) {
                        if(route.getDistance()>longueur) listeRoute.remove(route);
                    }
                }
                matrix.get(i).add(j,new Vertex(lieu2,listeRoute,longueur));
                j++;
            }
            i++;
        }

        for(int k = 0 ; k < size ; k++){
            for( i = 0 ; i < size ; i++){
                for( j = 0 ; j < size ; j++){
                    if(matrix.get(i).get(j).getDistance() > matrix.get(i).get(k).getDistance() + matrix.get(k).get(j).getDistance()
                    && matrix.get(k).get(j).getDistance() != INFINITE
                    &&matrix.get(i).get(k).getDistance() != INFINITE){
                        matrix.get(i).get(j).setDistance(matrix.get(i).get(k).getDistance() + matrix.get(k).get(j).getDistance());
                        matrix.get(i).get(j).setNextVertexIndex(k);
                    }
                }
            }
        }



        for ( i = 0 ; i<size ; i++){
            for ( j = 0 ; j<size ; j++){
                System.out.print(matrix.get(i).get(j).getDistance()+"|");
            }
            System.out.println("");
        }


    }

    public ArrayList<TRoute> findWay(TLieu lieu1, TLieu lieu2){
        ArrayList<TRoute> chemin = new ArrayList<TRoute>();
        int i, j;
        for(i = 0; i<size ;i++)
        {
            if(matrix.get(i).get(i).getDepart() == lieu2) break;
        }
        for(j = 0; j<size ;j++)
        {
            if(matrix.get(i).get(j).getDepart() == lieu1) break;
        }
        if(matrix.get(i).get(j).getDistance() == INFINITE) return null;
        while (matrix.get(i).get(j).getDepart() != lieu2){
            chemin.addAll(matrix.get(i).get(j).getListRoute());
            j = matrix.get(i).get(j).getNextVertexIndex();
        }
        return chemin;
    }
}
