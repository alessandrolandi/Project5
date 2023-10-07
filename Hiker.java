package project5;


/*
 * Hiker class that represents a hiker traversing down
 * the BSTmountain. Keeps track of supplies encountered.
 *
 */
public class Hiker {

    int axe = 0;
    int food = 0;
    int raft = 0;


    public Hiker(){

        this.axe = axe;
        this.food = food;
        this.raft = raft;


    }

    /*
     * Method to return a duplicate Hiker of this Hiker.
     * @return duplicate of this Hiker.
     */

    public Hiker duplicate(){
        Hiker duplicate = new Hiker();
        duplicate.food = this.getFood();
        duplicate.axe = this.getAxe();
        duplicate.raft = this.getRaft();

        return duplicate;

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

}
