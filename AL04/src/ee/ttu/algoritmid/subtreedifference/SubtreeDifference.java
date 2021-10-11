package ee.ttu.algoritmid.subtreedifference;

public class SubtreeDifference {

    /**
     * Calculate difference between sum of all left children and sum of all right children for every node
     *
     * @return root node of the tree where for every node is computed difference of sums of it's left and right children
     */
    public long calculateDifferences(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.getLeft() != null && node.getRight() != null) {
//            node.setSumOfAllChildren();
            node.setDifferenceOfLeftAndRight(node.getLeft().getValue() - node.getRight().getValue());
            return (calculateDifferences(node.getLeft()) - calculateDifferences(node.getRight()));
        } else {
            node.setDifferenceOfLeftAndRight(0);
        }
        return node.getDifferenceOfLeftAndRight();

    }


    public Node calculate(Node rootNode) {
        calculateDifferences(rootNode);
        return rootNode;
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
        solution.calculate(rootNode);
        System.out.println(rootNode.getDifferenceOfLeftAndRight());
        System.out.println(a.getDifferenceOfLeftAndRight());//-10
        System.out.println(b.getDifferenceOfLeftAndRight());    // -20
        System.out.println(c.getDifferenceOfLeftAndRight());     // 0


        System.out.println("Your solution should be working fine in basic cases, try to push.");

    }

}