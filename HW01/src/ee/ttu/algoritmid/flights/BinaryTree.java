package ee.ttu.algoritmid.flights;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree class with nodes.
 */
public class BinaryTree {
    private Node root;
    private List<FlightCrewMember> members = new ArrayList<>();


    /**
     * Add a node to the tree (which has a crew member as its value).
     * @param member value of the node to be added
     */
    public void addMember(FlightCrewMember member) {
        if (member == null || member.getName().isEmpty() || member.getName() == null ||
                member.getWorkExperience() < 0 || member.getRole() == null) {
            throw new IllegalArgumentException();
        }
        root = addAChildNode(root, member);
    }

    /**
     * A private recursive method to use for the AddMember method (adding a child node).
     * @param current parent node
     * @param member value of the node to be added
     * @return current parent node
     */
    private Node addAChildNode(Node current, FlightCrewMember member) {
        if (current == null) return new Node(member);
        if (member == null) return current;
        if (member.getWorkExperience() <= current.member.getWorkExperience()) {
            current.left = addAChildNode(current.left, member);
        } else current.right = addAChildNode(current.right, member);
        return rebalance(current);
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
        getAllTreeValues(node.left);
        this.members.add(node.member);
        getAllTreeValues(node.right);
    }

    /**
     * Delete a node with the given member value.
     * @param member to delete from the tree
     */
    public void deleteMember(FlightCrewMember member) { root = deleteChildNode(root, member); }

    /**
     * Delete a member using recursive function.
     * @param root current node
     * @param member to delete
     * @return root
     */
    private Node deleteChildNode(Node root, FlightCrewMember member) {
        if (root == null) return null;
        if (member.getWorkExperience() < root.member.getWorkExperience())
            root.left = deleteChildNode(root.left, member);
        else if (member.getWorkExperience() > root.member.getWorkExperience())
            root.right = deleteChildNode(root.right, member);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.member = findSmallestValue(root.right);
            root.right = deleteChildNode(root.right, root.member);
        }

        return rebalance(root);
    }

    /**
     * Find the member with a smallest value (down the tree to the left)
     * @param root node
     * @return member
     */
    private FlightCrewMember findSmallestValue(Node root) {
        FlightCrewMember minval = root.member;
        while (root.left != null) {
            minval = root.left.member;
            root = root.left;
        }
        return minval;
    }

    /**
     * Find a node with a value >= N.
     * @param n int
     * @return node
     */
    public FlightCrewMember findValueMoreOrEqualN(double n) {
        return findNodeRecursive(root, n, true, null);
    }

    /**
     * Find a node with a value <= N.
     * @param n int
     * @return node
     */
    public FlightCrewMember findValueLessOrEqualN(double n) {
        return findNodeRecursive(root, n, false, null);
    }

    /**
     * Find a node with the value in the range [n, k].
     *
     * @param n min value
     * @param k max value
     * @return crew member
     */
    public FlightCrewMember findValueInRange(double n, double k, boolean closerToK) {
        return findNodeRecursiveInRange(root, k, n, closerToK, null);
    }

    /**
     * Find a node with a member's exp that is either more or less than provided exp.
     *
     * @param current node
     * @param exp value
     * @param findNodeWithMoreExp true if more, false if less
     * @return flight crew member or null. sss
     */
    private FlightCrewMember findNodeRecursive(Node current, double exp, boolean findNodeWithMoreExp,
                                               FlightCrewMember member) {
        if (current == null) return member;
        double memberExp = current.member.getWorkExperience();
        if (exp == memberExp) return current.member;
        if (findNodeWithMoreExp) {
            if (memberExp < exp && current.right != null) {
                return findNodeRecursive(current.right, exp, true, member);
            } else if (memberExp >= exp) {
                if (member == null || member.getWorkExperience() - memberExp > 0) member = current.member;
                if (current.left != null) return findNodeRecursive(current.left, exp, true, member);
                return member;
            }
        } else {
            if (memberExp > exp && current.left != null) {
                return findNodeRecursive(current.left, exp, false, member);
            } else if (memberExp <= exp) {
                if (member == null || member.getWorkExperience() - memberExp < 0) member = current.member;
                if (current.right != null) return findNodeRecursive(current.right, exp, false, member);
                return member;
            }
        }
        return member;
    }

    private FlightCrewMember findNodeRecursiveInRange(Node current, double maxExp, double minExp, boolean closerToMax,
                                                      FlightCrewMember member) {
        if (current == null) return member;
        double memberExp = current.member.getWorkExperience();
        if (closerToMax) {
            if (memberExp == maxExp) return current.member;
            if (memberExp < minExp && current.right != null) {
                return findNodeRecursiveInRange(current.right, maxExp, minExp, true, member);
            } else if (current.left != null && memberExp > maxExp) {
                return findNodeRecursiveInRange(current.left, maxExp, minExp, true, member);
            } else if (memberExp <= maxExp && memberExp >= minExp) {
                if (member == null || member.getWorkExperience() - memberExp < 0) member = current.member;
                if (current.right != null) return findNodeRecursiveInRange(current.right, maxExp, minExp, true, member);
                return member;
            }
        } else {
            if (memberExp == minExp) return current.member;
            if (memberExp < minExp && current.right != null) {
                return findNodeRecursiveInRange(current.right, maxExp, minExp, false, member);
            } else if (current.left != null && memberExp > maxExp) {
                return findNodeRecursiveInRange(current.left, maxExp, minExp, false, member);
            } else if (memberExp <= maxExp && memberExp >= minExp) {
                if (member == null || member.getWorkExperience() - memberExp > 0) member = current.member;
                if (current.left != null) return findNodeRecursiveInRange(current.left, maxExp, minExp, false, member);
                return member;
            }
        }
        return member;
    }

    private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }
}
