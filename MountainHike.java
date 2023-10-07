package project5;

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.Scanner;


/*
 * This program uses an AVL Binary Search Tree to model a mountain hiking expedition.
 * A Hiker starts at the top of the mountain. They need to find their way down the mountain with
 * limited resources. The program produces a list of all the possible paths the hiker should take.
 * The mountain is represented by an AVL Binary Search Tree. Nodes in the tree represent a rest-stop
 * that contain supplies and possible obstacles. Some of the supplies may help with obstacles-- if they do
 * the supply is exhausted. The paths from the root down represent possible trails. The hiker can only go down.
 * Not all paths lead to the bottom of the mountain.
 *
 */


public class MountainHike {

    /*
     * Recursive method to find all valid paths the hiker can take. Uses preorder traversal to
     * traverse the BSTMountain (AVL Tree). Valid paths are paths the hiker can take when the hiker
     * has appropriate supplies for traversal: food for each edge and an axe if there is a fallen tree
     * and a raft if there is a river. The items are exhausted after each use.
     *
     * The function returns an array list (of strings) of all the valid paths
     *
     * @param Node node is the node that is traversed
     * @param String solution is the path
     * @param Hiker hiker is the hiker that is traversing the path
     * @param int food is the amount of food the hiker has.
     * @param int max is the maxDepth of the BSTMountain that is being traversed
     * @param ArrayList<String> paths is the arraylist of paths that is returned
     * @returns ArrayList<String> paths of all valid paths given the conditions.
     */

    public static ArrayList<String> findPaths ( Node node , String solution, Hiker hiker, int food, int max, ArrayList<String> paths ){
        //if it reaches a cliff or bottom of the mountain.
        if( node == null ){
            //if it reaches the bottom of the mountain (not a cliff) add to the solutions, otherwise return
            if(max == -1){
                paths.add(solution);
                return paths;
            } else{
                return paths;
            }

        }

        hiker.food = hiker.food + node.reststop.getFood();
        hiker.axe = hiker.axe + node.reststop.getAxe();
        hiker.raft = hiker.raft + node.reststop.getRaft();

        int comp_axe = hiker.axe - node.reststop.getTree();
        int comp_raft = hiker.raft - node.reststop.getRiver();

        //add to solutions
        solution = solution + " " + node.getLabel();

        //conditions for invalid path, returns if true
        if(hiker.food < 0 || comp_axe < 0 || comp_raft < 0){
            return paths;
        }

        //if the hiker uses an axe, the axe is exhausted
        if(hiker.axe > 0 && comp_axe == 0){
            hiker.axe--;
        }

        //if the hiker uses a raft, the raft is exhausted
        if(hiker.raft > 0 && comp_raft == 0){
            hiker.raft--;
        }

        //food is exhausted from moving
        hiker.food--;

        //duplicate hikers to traverse possibly invalid or valid paths.
        Hiker hiker1 = hiker.duplicate();
        Hiker hiker2 = hiker.duplicate();

        findPaths(node.left, solution, hiker1, hiker1.food, max - 1, paths);
        findPaths(node.right, solution, hiker2, hiker2.food, max - 1, paths);

        return paths;

    }


    public static void main(String [] args ){


        //verify that the command line argument exists

        if(args.length == 0){
            System.err.println("Usage Error: No argument found.");
            System.exit(1);
        }


        File dataFile = new File(args[0]);

        //verify that command line argument contains a name of an existing file

        if(!dataFile.exists()){
            System.err.println("Error: the file does not exist.");
            System.exit(1);
        }
        if(!dataFile.canRead()){
            System.err.println("Error: the file cannot be read.");
            System.exit(2);
        }

        //open the file for reading

        Scanner inData = null;

        try {
            inData = new Scanner (dataFile);
        } catch (FileNotFoundException e){
            System.err.println("Error: the file cannot be found.");

            System.exit(3);
        }
        String line = null;
        String [] node_input = null;
        Scanner parseLine = null;

        BSTMountain mountain = new BSTMountain();



        /*
         * Iterates through file and creates nodes and RestStops
         * to be added to the BSTMountain mountain.
        */

        while(inData.hasNextLine()){

            line = inData.nextLine();
            node_input =  line.split(" ");

            // Catches if the label file isn't formatted correctly

            if( node_input[0].length() > 1 ){
                System.err.println("Error: incorrect format for label" );
            }

            char label = node_input[0].charAt(0);
            RestStop stop = new RestStop (node_input);
            mountain.add( label, stop );


        }



        ArrayList<String> paths = new ArrayList<>();
        Hiker hiker = new Hiker();

        int max = mountain.maxDepth(mountain.getRoot());

        //call to recursive method to find all valid paths the hiker can take.

        paths = findPaths (mountain.getRoot(), "", hiker, 0, max, paths );

        for(int i = 0; i < paths.size(); i+=2){
            System.out.println(paths.get(i).substring(1, paths.get(i).length()));
        }

    }
}
