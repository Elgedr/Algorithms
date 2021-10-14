package ee.ttu.algoritmid.subtreedifference;

public class SubtreeDifference {

    /**
     * Calculate difference between sum of all left children and sum of all right children for every node
     *
     * @return root node of the tree where for every node is computed difference of sums of it's left and right children
     */
    public Node calculateDifferences(Node node) {
        if (node.getLeft() != null && node.getRight() != null) {
            long leftsum = calculateDifferences(node.getLeft()).getSumOfAllChildren();
            long rightsum = calculateDifferences(node.getRight()).getSumOfAllChildren();
            node.setSumOfAllChildren(node.getValue() + leftsum + rightsum);
            node.setDifferenceOfLeftAndRight(leftsum - rightsum);
        } else if (node.getLeft() == null && node.getRight() != null) {
            long leftsum = 0;
            long rightsum = calculateDifferences(node.getRight()).getSumOfAllChildren();
            node.setSumOfAllChildren(node.getValue() + leftsum + rightsum);
            node.setDifferenceOfLeftAndRight(leftsum - rightsum);

        } else if (node.getRight() == null && node.getLeft() != null) {
            long leftsum = calculateDifferences(node.getLeft()).getSumOfAllChildren();
            long rightsum = 0;
            node.setSumOfAllChildren(node.getValue() + leftsum + rightsum);
            node.setDifferenceOfLeftAndRight(leftsum - rightsum);
        } else {
            node.setDifferenceOfLeftAndRight(0);
            node.setSumOfAllChildren(node.getValue());
        }
        return node;

    }


    public static void main(String[] args) throws Exception {
        /**
         *  Use this example to test your solution
         *                  Tree:
         *                   15
         *               /       \
         *             10         17
         *           /   \       /  \
         *         3     13     5    25
         */
        Node rootNode = new Node(15);
        Node a = new Node(10);
        Node b = new Node(17);
        Node c = new Node(3);
        Node d = new Node(13);
        Node e = new Node(5);
        Node f = new Node(25);

        rootNode.setLeft(a);
        rootNode.setRight(b);
        a.setLeft(c);
        a.setRight(d);
        b.setLeft(e);
        b.setRight(f);

        SubtreeDifference solution = new SubtreeDifference();
        solution.calculateDifferences(rootNode);
        System.out.println(rootNode.getDifferenceOfLeftAndRight()); //- 21
        System.out.println(a.getDifferenceOfLeftAndRight());//-10
        System.out.println(b.getDifferenceOfLeftAndRight());    // -20
        System.out.println(c.getDifferenceOfLeftAndRight());     // 0


        System.out.println("Your solution should be working fine in basic cases, try to push.");

    }

}