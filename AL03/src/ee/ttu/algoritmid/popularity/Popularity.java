package ee.ttu.algoritmid.popularity;

import java.util.Map;

public class Popularity {
    public Map<String, Integer> coordinates;

    public Popularity(int maxCoordinates) {
    }

    /**
     * @param x, y - coordinates
     */
    void addPoint(Integer x, Integer y) {
        String coordStr = x.toString() + ";" + y.toString();
        if (coordinates.containsKey(coordStr)) {
            coordinates.put(coordStr, coordinates.get(coordStr) + 1);
        } else coordinates.put(coordStr, 1);
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

}