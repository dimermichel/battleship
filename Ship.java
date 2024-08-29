package battleship;

import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final Type type;
    private String[] coordinates;
    private Set<String> hits;
    private boolean isSunk;

    public Ship(Type type){
        this.type = type;
        this.coordinates = new String[type.getLength()];
        this.hits = new HashSet<>();
        this.isSunk = false;
    }

    public Type getType(){
        return type;
    }
    
    public String[] getCoordinates(){
        return coordinates;
    }

    public Set<String> getHits(){
        return hits;
    }

    public boolean getIsSunk(){
        return isSunk;
    }

    void setCoordinate(String coordinate, int position){
        this.coordinates[position] = coordinate;
    }

    void setHit(String coordinate){
        hits.add(coordinate);
        if(hits.size() == type.getLength()){
            isSunk = true;
        }
    }
}
