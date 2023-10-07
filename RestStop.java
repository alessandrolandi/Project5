package project5;

/*
 * RestStop class that represents the number of items and obstacles
 * at each node.
 * Contains the number of food, axes, rafts, rivers and fallen trees.
 *
 */

public class RestStop {

    private char label;
    private int food = 0;
    private int axe = 0;
    private int raft = 0;
    private int river = 0;
    private int tree = 0;

    /*
     * Constructor for RestStop that takes an array created
     * from reading through the input file and adds to the appropriate
     * supplies and obstacles when the appropriate string is found
     * If a supply is found after an obstacle is encountered then it's ignored.
     * It is case-sensitive.
     *
     */

    public RestStop (String [] arr){

        boolean obstacle = false;

        if(arr.length > 0) {
            this.label = arr[0].charAt(0);
            for (int i = 1; i < arr.length; i++) {
                if (arr[i].equals("food")){
                    if(obstacle == true ){
                        continue;
                    } else{
                        this.food++;
                        continue;
                    }
                } else if (arr[i].equals("axe")){
                    if(obstacle == true ){
                        continue;
                    } else{
                        this.axe++;
                        continue;
                    }
                } else if (arr[i].equals("raft")){
                    if(obstacle == true ){
                        continue;
                    } else{
                        this.raft++;
                        continue;
                    }
                } else if (arr[i].equals("river")){
                    this.river++;
                    obstacle = true;
                    continue;
                } else if (arr[i].equals("fallen")){
                    this.tree++;
                    i++;
                    obstacle = true;
                    continue;
                }
            }
        }


    }

    public char getLabel(){
        return this.label;
    }
    public int getFood(){
        return this.food;
    }
    public int getAxe(){
        return this.axe;
    }
    public int getRaft(){
        return this.raft;
    }
    public int getRiver(){
        return this.river;
    }
    public int getTree(){
        return this.tree;
    }


}

