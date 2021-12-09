package ee.ttu.algoritmid.bond;

public class AL07 {

    public enum Network {
        FRIENDLY, UNFRIENDLY, UNKNOWN;
    }

    private DisjointSubsets disjointSubsets = new DisjointSubsets();

    public AL07() {
        // don't remove
    }

    public DisjointSubsets getDisjointSubsets() {
        return disjointSubsets;
    }

    public void talkedToEachOther(String name1, String name2) {
        disjointSubsets.union(name1, name2);
    }

    public void addPerson(String name) {
        disjointSubsets.addSubset(name);
    }

    public void friendly(String name) {

    }

    public void unfriendly(String name) {

    }

    public Network memberOfNetwork(String name) {
        String person = disjointSubsets.find(name);
        if (person != null) {
            return Network.FRIENDLY;
        } else {
            return Network.UNFRIENDLY;
        }

    }

}