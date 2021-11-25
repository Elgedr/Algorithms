package ee.ttu.algoritmid.flights;

/**
 * Node class for the binary tree.
 */
public class Node {
    protected FlightCrewMember member;
    protected Node left;
    protected Node right;
    int height;

    protected Node(FlightCrewMember member) {
        this.member = member;
        right = null;
        left = null;
    }


}
