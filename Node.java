package project5;


/*
 * Node class for BST
 * contains height of node, the label, and
 * an assigned RestStop.
 */

public class Node {
    char label;
    Node left;
    Node right;
    int height;

    RestStop reststop;

    public Node (char label){
        this.label = label;
    }


    public char getLabel(){
        return this.label;
    }

}
