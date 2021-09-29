package ee.ttu.algoritmid.popularity;

import java.util.HashMap;
import java.util.Map;

public class Popularity {
    public Map<String, Integer> coordinates = new HashMap<>();

    public Popularity(int maxCoordinates) {
    }

    /**
     * @param x, y - coordinates
     */
    void addPoint(Integer x, Integer y) {
        String coordStr = x.toString() + ";" + y.toString();
        Integer popul = coordinates.get(coordStr);
        if (popul != null) {
            coordinates.put(coordStr, coordinates.get(coordStr) + 1);

        } else {
            coordinates.put(coordStr, 1);
        }
    }

    /**
     * @param x, y - coordinates
     * @return the number of occurrennces of the point
     */
    int pointPopularity(Integer x, Integer y) {
        String coordStr = x.toString() + ";" + y.toString();
        return coordinates.get(coordStr);
    }


    /**
     * @return the number of occurrennces of the most popular point
     */
    int maxPopularity() {
        Integer popularity = 0;
        for (Integer val : coordinates.values()) {
            if (val > popularity) {
                popularity = val;
            }
        }
        return popularity;
    }

    public static void main(String[] args) {
        var p = new Popularity(100000);
        p.addPoint(4, 4);
        p.addPoint(4, 4);
        p.addPoint(4, 4);
        p.addPoint(4, 4);
        p.addPoint(47, 7);
        p.addPoint(47, 7);
        p.addPoint(47, 7);


    }

}