package PhysicalStructures;

import java.util.Random;

public class Dice {
    private int dicenumber;

    // default constructor
    public Dice() {
    }

    // create dice with inital number
    // only needed for Usefullfunctions
    public Dice(int dicenumber) {
        this.dicenumber = dicenumber;
    }


    /**
     *  simulates throwing a Dice and safe and return the number that was thrown
     * @return number that was thrown
     */
    public int throwDice(){
        Random generator = new Random();
        this.dicenumber = generator.nextInt(6) + 1;
        return this.dicenumber;
    }

    // comment in for testing issues
    /*
    public void setDicenumber(int dicenumber) {
        this.dicenumber = dicenumber;
    }
    */


    public int getDicenumber() {
        return dicenumber;
    }
}