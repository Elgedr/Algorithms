package ee.ttu.algoritmid.flights;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree class with nodes.
 */
public class BinaryTree {
    private int size = 0;
    private Node root;
    private List<FlightCrewMember> members = new ArrayList<>();

    /**
     * Get the number of all nodes in the tree.
     * @return how many nodes the tree has
     */
    public int getTreeSize() { return size; }

    /**
     * Add a node to the tree (which has a CrewMember as its value).
     * @param member value of the node to be added
     */
    public void addMember(FlightCrewMember member) {
        root = addAChildNode(root, member);
        size += 1;
    }

    /**
     * A method to return all the members in the nodes of the tree.
     * @return List of members
     */
    public List<FlightCrewMember> getAllMembers() {
        this.members = new ArrayList<>();
        getAllTreeValues(root);
        return this.members;
    }

    /**
     * Private method of accessing all values in the tree.
     * @param node current root node
     */
    private void getAllTreeValues(Node node) {
        if (node == null) return;
        this.members.add(node.member);
        getAllTreeValues(node.left);
        getAllTreeValues(node.right);
    }

    /**
     * A private recursive method to use for the AddMember method (adding a child node).
     * @param current parent node
     * @param member value of the node to be added
     * @return current parent node
     */
    private Node addAChildNode(Node current, FlightCrewMember member) {
        if (current == null) return new Node(member);
        if (member.getWorkExperience() <= current.member.getWorkExperience()) {
            current.left = addAChildNode(current.left, member);
        } else current.right = addAChildNode(current.right, member);
        return current;
    }

    /**
     * Delete a node with the given member value.
     * @param member to delete from the tree
     * @return boolean (succesfully deleted or not)
     */
    public boolean deleteMember(FlightCrewMember member) {
        return deleteChildNode(root, member);
    }

    /**
     * A private recursive method to use in the deleteMember function.
     * @param current parent node
     * @param member to delete from the tree
     * @return boolean (successfully deleted or not)
     */
    private boolean deleteChildNode(Node current, FlightCrewMember member) {
        if (current == null) return false;
        if (current.member == member) {
            current.member = null;
            size -= 1;
            return true;
        }
        if (member.getWorkExperience() <= current.member.getWorkExperience()) {
            deleteChildNode(current.left, member);
        } else deleteChildNode(current.right, member);
        return false;
    }

    /**
     * Find a node with a value >= N.
     * @param n int
     * @return node
     */
    public FlightCrewMember findValueMoreOrEqualN(double n) {
        return findNodeRecursive(root, n, true);
    }

    /**
     * Find a node with a value <= N.
     * @param n int
     * @return node
     */
    public FlightCrewMember findValueLessOrEqualN(double n) {
        return findNodeRecursive(root, n, false);
    }

    /**
     * Find a node with the value in the range [n, k].
     *
     * @param n min value
     * @param k max value
     * @return crew member
     */
    public FlightCrewMember findValueInRange(double n, double k) {
        FlightCrewMember member = findNodeRecursiveInRange(root, n, k);
        System.out.println(member);
        return member;
    }

    /**
     * Find a node with a member's exp that is either more or less than provided exp.
     *
     * @param current node
     * @param exp value
     * @param findNodeWithMoreExp true if more, false if less
     * @return flight crew member or null
     */
    private FlightCrewMember findNodeRecursive(Node current, double exp, boolean findNodeWithMoreExp) {
        if (current == null) return null;
        double memberExp = current.member.getWorkExperience();
        if (exp == memberExp) return current.member;
        if (findNodeWithMoreExp) {
            if (memberExp > exp) {
                return current.member;
            } else return findNodeRecursive(current.right, exp, true);
        } else {
            if (memberExp < exp) {
                return current.member;
            } return findNodeRecursive(current.left, exp, false);
        }
    }

    /**
     * Find a node in a given range [minExp, maxExp].
     * @param current parent node
     * @param minExp the member can have
     * @param maxExp the member can have
     * @return node
     */
    private FlightCrewMember findNodeRecursiveInRange(Node current, double minExp, double maxExp) {
        if (current == null) return null;
        double memberExp = current.member.getWorkExperience();
        if (minExp == memberExp || maxExp == memberExp) return current.member;
        if (memberExp > minExp && memberExp < maxExp) { return current.member; }
        if (memberExp > minExp) {
            return findNodeRecursiveInRange(current.left, minExp, maxExp);
        } else return findNodeRecursiveInRange(current.right, minExp, maxExp);
    }
}
