package project5;

import static java.lang.Math.max;

/*
 * BSTMountain Class that creates an AVL tree. BSTMountain extends
 * from the general Binary Search Tree Class.
 * Contains methods to balance the general Binary Search Tree
 * to create an AVL Tree.
 *
 */

public class BSTMountain extends BST {

    /*
     * Returns the height of the node. If the node doesn't have a
     * height then returns -1
     * @returns height of the node of -1 if the node is null.
     */
    private int height(Node node ){
        return node != null ? node.height : -1;
    }

    /*
     * Returns the height of the node. If the node doesn't have a
     * height then returns -1
     * @param Node node
     * @returns height of the node of -1 if the node is null.
     */

    private void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = max(leftChildHeight, rightChildHeight) + 1;

    }

    /*
     * Returns the difference between a left child node and a right child node.
     * @param Node node to find the balance factor of child nodes.
     * @returns the difference between node.right and node.left.
     */

    private int balanceFactor(Node node){
        return height(node.right) - height(node.left);
    }

    /*
     * Method to perform right rotation on an unbalanced subtree
     * Returns the new root node
     * @param Node node to be balanced
     * @returns the new root node
     */

    private Node rotateRight(Node node){
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    /*
     * Method to perform left rotation on an unbalanced subtree
     * Returns the new root node
     * @param Node node to be balanced
     * @returns the new root node
     */

    private Node rotateLeft(Node node){
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;

    }

    /*
     * Method to balance entire subtree
     * Returns the new root node
     * @param Node node to be balanced
     * @returns the new root node to the subtree
     */

    private Node rebalance(Node node){
        int balanceFactor = balanceFactor(node);

        // Left heavy subtree
        if ( balanceFactor < - 1){
            if(balanceFactor(node.left) <= 0){ //rr
                //Rotate right around node
                node = rotateRight(node);
            } else {
                //Rotate right left  around node
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        // Right heavy subtree
        if (balanceFactor > 1) {
            if(balanceFactor(node.right) >= 0){ //rl
                //Rotate left around node
                node = rotateLeft(node);
            } else {
                //Rotate right left around node
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }

    /*
     * Method to add node to BSTMountain (AVL Tree)
     * Balances BSTMountain as nodes are added
     * @param char label to assign node to be added
     * @param RestStop stop to be assigned to node
     * @param Node node to be added
     * @returns returns balanced node to AVL
     */

    @Override
    public Node add( char label, RestStop stop, Node node){

        node = super.add(label, stop, node);

        updateHeight(node);

        return rebalance(node);

    }

}
