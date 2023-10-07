package project5;


/*
 * General Binary Search Tree Class for BSTMountain (AVL Tree)
 * contains a reference to the root and functions to find the maxDepth
 * and recursive methods to add nodes to the tree.
 */

public class BST {
    private Node root;      //reference to the root of the node.
    private boolean added;      //helper variable used by the add method.

    /*
     * Returns the root of this binary search tree.
     * @return the root of this binary search tree.
     */
    public Node getRoot(){
        return root;
    }

    /*
     * Returns the max depth of this tree.
     * @return the max depth of this tree
     */
    int maxDepth(Node node)
    {
        if (node == null)
            return -1;
        else
        {
            /* compute the depth of each subtree */
            int left_Depth = maxDepth(node.left);
            int right_Depth = maxDepth(node.right);

            /* use the larger one */
            if (left_Depth > right_Depth)
                return (left_Depth + 1);
            else
                return (right_Depth + 1);
        }
    }

    /*
     * Adds the specified element to this tree if it is not already present.
     * If this tree already contains the element, the call leaves the
     * tree unchanged and returns false.
     * @param char label to be added to this tree
     * @param RestStop to be assigned to the node
     * @return true if this tree did not already contain the specified element
     * @throws NullPointerException if the specified element is null
     */

    public boolean add ( char label, RestStop stop ) {
        added = false;
        if (label == ' ') throw new NullPointerException ("null value found");
        //replace root with the reference to the tree after the new
        //value is added
        root = add (label, stop, root);
        return added;
    }


    /*
     * Actual recursive implementation of add.
     *
     * This function returns a reference to the subtree in which
     * the new value was added.
     *
     * @param char label to be added to this tree
     * @param RestStop stop to be assigned to added node
     * @param node node at which the recursive call is made
     */

    public Node add (char label, RestStop stop, Node node ) {
        if (node == null) {
            added = true;
            Node new_node = new Node(label);
            new_node.reststop = stop;

            return  new_node;
        }

        //find the location to add the new value
        if (Character.compare(label, node.label) < 0 ) { //add to the left subtree
            node.left = add(label, stop, node.left);
        }
        else if (Character.compare(label, node.label) > 0 ) { //add to the right subtree
            node.right = add(label, stop, node.right);
        }
        else { //duplicate found, do not add
            added = false;
            //return node;
        }


        return node;
    }

}
