package PhysicalStructures;

import Structures.UsefullFunctions;

import java.util.ArrayList;

public class DiceCup {
    private Dice[] content;
    private boolean[] safe;

    public DiceCup(){
        this.content = new Dice[5];
        // init content (dices)
        for (int i = 0; i < content.length; i++) {
            content[i] = new Dice();
        }

        // no need to init this array cause java does it by itself with false
        this.safe = new boolean[5];
    }

    public int[] getAllDices() {
        ArrayList<Integer> ret = new ArrayList<>();
        for (Dice dice: this.content) {
            // if dice was already thrown
            if(dice.getDicenumber() != 0) {
                ret.add(dice.getDicenumber());
            }else {
                // if one was not thrown every one wasnt thrown beacuse in this game u can only throw every dice in ur first throw
                return UsefullFunctions.makeArrayListToArray(ret);
            }
        }


        return UsefullFunctions.makeArrayListToArray(ret);
    }

    /**
     *
     * @return Dices that are currently safed
     */
    public ArrayList<Integer> getSafedDices() {
        ArrayList<Integer> safedDicesNumbers = new ArrayList<>();

        for (int i = 0; i < this.safe.length; i++) {
            if (this.safe[i]) {
                safedDicesNumbers.add(this.content[i].getDicenumber());
            }
        }

        return safedDicesNumbers;
    }

    /**
     * safes all dices with given dicenumber that will not be thrown, till this function is called again.
     * @param numbers numbers that u want to safe
     * @return number that has been safed
     */
    public ArrayList<Integer> safeDices(int[] numbers) {
        ArrayList<Integer> safedDicesNumbers = new ArrayList<>();



        for (int i = 0; i < numbers.length; i++) {

            // mark inidicies from safed Dices
            Dice markedDice = this.markDiceAsSafe(numbers[i]);
            // if trying to safe dice hasnt gone wrong
            if (markedDice != null) {
                safedDicesNumbers.add(markedDice.getDicenumber());
            }
            // break because u have to save every number just ones
            // break;

        }

        // return Safed Dices as Notice for Player
        return safedDicesNumbers;
    }

    /**
     * the selection of saved dices will be deleted
     * @return empty List cause no Dices are Saved
     */
    public ArrayList<Integer> deleteSafedDices() {
        // change this.safe field to false, cause selection want to be changed
        // just need to set this.safe to a new boolean Array cause its initalized to false by java
        this.safe = new boolean[this.safe.length];
        return new ArrayList<Integer>();
    }

    /**
     * simulates one Throw of a turn
     * @return return dices that was thrown
     */
    public ArrayList<Integer> simulateThrowingAllNotSafedDices() {
        ArrayList<Integer> whatWasThrown = new ArrayList<>();
        for (int i = 0; i < this.content.length; i++) {
            if(!this.safe[i]) {
                whatWasThrown.add(this.content[i].throwDice());
            }
        }
        return whatWasThrown;
    }

    /**
     *
     * @param number Dice with that number will be safed
     * @return safed Dice or null if none Dice can be safed
     */
    private Dice markDiceAsSafe(int number) {
        for (int i = 0; i < content.length; i++) {
            if (content[i].getDicenumber() == number && !safe[i]) {
                safe[i] = true;
                return content[i];
            }
        }
        return null;
    }

    /**
     * resets Values for next turn
     * make it return boolean cause of functionial programming Style
     */
    public boolean resetValues() {
        this.content = new Dice[5];
        // init content (dices)
        for (int i = 0; i < content.length; i++) {
            content[i] = new Dice();
        }

        // no need to init this array cause java does it by itself with false
        this.safe = new boolean[5];

        return true;
    }
}