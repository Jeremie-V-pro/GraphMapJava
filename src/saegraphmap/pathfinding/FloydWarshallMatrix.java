package saegraphmap.pathfinding;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TListe;
import saegraphmap.linkedlist.TRoute;

import java.util.ArrayList;

public class FloydWarshallMatrix {
    private final ArrayList<ArrayList<Vertex>> matrix = new ArrayList<>();
    private float INFINITE = Float.MAX_VALUE;

    private final int size;

    public FloydWarshallMatrix(TListe liste){
        size = liste.compterLieu();
        float longueur = INFINITE;
        int nextindex;
        for(int i = 0; i <size ; i++){
            matrix.add(new ArrayList<>(size));
            System.out.println(i);
        }
        int i = 0;
        int j;
        for(TLieu lieu1 = liste.getListe(); lieu1 != null; lieu1= lieu1.getSuivant()){
            j = 0;
            ArrayList<TRoute>listeRoute;
            for(TLieu lieu2 = liste.getListe(); lieu2 != null; lieu2=lieu2.getSuivant(), longueur = INFINITE){
                if(lieu1 == lieu2){
                    longueur = 0;
                    listeRoute = null;
                    nextindex = -1;
                }
                else {
                    listeRoute = liste.chercheRoutes(lieu1.getNomLieu(),lieu2.getNomLieu());
                    for (TRoute route: listeRoute) {
                        if(route.getDistance()<longueur) longueur=route.getDistance();
                    }
                    for (TRoute route: listeRoute) {
                        if(route.getDistance()>longueur) listeRoute.remove(route);
                    }
                    nextindex = i;
                }
                matrix.get(i).add(j,new Vertex(lieu2,longueur, nextindex));
                j++;
            }
            i++;
        }
        



        for(int k = 0 ; k < size ; k++){
            for( i = 0 ; i < size ; i++){
                for( j = 0 ; j < size ; j++){
                    if(matrix.get(i).get(j).getDistance() > (matrix.get(i).get(k).getDistance() + matrix.get(k).get(j).getDistance())
                    && matrix.get(k).get(j).getDistance() != INFINITE
                    && matrix.get(i).get(k).getDistance() != INFINITE){
                        matrix.get(i).get(j).setDistance(matrix.get(i).get(k).getDistance() + matrix.get(k).get(j).getDistance());
                        matrix.get(i).get(j).setNextVertexIndex(k);
                    }
                }
            }
            for( int u = 0 ; u < size ; u++){
                for( int v = 0 ; v < size ; v++) {
                    System.out.print(matrix.get(u).get(v).getDepart().getNomLieu()+","+matrix.get(u).get(v).getDistance()+ ","+ matrix.get(u).get(v).getNextVertexIndex()+"|");
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
        }
    }

    public float findWay(TLieu lieu1, TLieu lieu2){
        float distance = 0;
        int i, j;
        for(i = 0; i<size ;i++)
        {
            if(matrix.get(i).get(i).getDepart() == lieu2) break;
        }
        for(j = 0; j<size ;j++)
        {
            if(matrix.get(i).get(j).getDepart() == lieu1) break;
        }
        if(matrix.get(i).get(j).getDistance() == INFINITE) return -1;
        while (i != j){
            for (TRoute route:matrix.get(i).get(j).getListRoute()) {
                route.setRoutePluscourChemin(true);
            }
            distance += matrix.get(i).get(j).getDistance();
            j = matrix.get(i).get(j).getNextVertexIndex();
        }
        return distance;
    }
}
