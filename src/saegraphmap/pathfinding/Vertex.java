package saegraphmap.pathfinding;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TRoute;

import java.util.ArrayList;

public class Vertex {
    private ArrayList<TRoute> listRoute;

    private TLieu depart;
    private float distance;

    private int nextVertexIndex;

    Vertex(TLieu depart, ArrayList<TRoute> listRoute, float distance){
        this.depart = depart;
        this.listRoute = listRoute;
        this.distance = distance;
    }

    public TLieu getDepart() {
        return depart;
    }

    public int getNextVertexIndex() {
        return nextVertexIndex;
    }

    public float getDistance() {
        return distance;
    }

    public void setNextVertexIndex(int nextVertexIndex) {
        this.nextVertexIndex = nextVertexIndex;
    }

    public ArrayList<TRoute> getListRoute() {
        return listRoute;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
