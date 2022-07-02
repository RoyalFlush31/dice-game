package PhysicalStructures;

import Exceptions.FatalSystemError;
import Structures.Field;
import Structures.FieldsForStreaksScoreboard;

import java.util.ArrayList;
import java.util.Arrays;

public class StreaksScoreboard extends Scoreboard {

    public StreaksScoreboard() {
        // StreakScoreboard has 7 Fields
        super(new Field[7]);

        //Filling Field Array with right fields for StreakScoreboard
        this.getFields()[0] = new Field(FieldsForStreaksScoreboard.THREEOFAKIND.toString());
        this.getFields()[1] = new Field(FieldsForStreaksScoreboard.FOUROFAKIND.toString());
        this.getFields()[2] = new Field(FieldsForStreaksScoreboard.SMALLSTRAIGHT.toString());
        this.getFields()[3] = new Field(FieldsForStreaksScoreboard.LARGESTRAIGHT.toString());
        this.getFields()[4] = new Field(FieldsForStreaksScoreboard.FULLHOUSE.toString());
        this.getFields()[5] = new Field(FieldsForStreaksScoreboard.YATZI.toString());
        this.getFields()[6] = new Field(FieldsForStreaksScoreboard.CHANCE.toString());
    }

    /**
     *
     * @return finalScore second Part of Scoreboard
     */
    public int getFinalScoreForStreaksScoreboard() {
        return this.getSumOfScoresFromAllFields();
    }

    public int setYatziScoreOnOtherFields(String keyOfField, int yatziMultiplier) throws FatalSystemError {
        // find field with right key
        for(Field field: this.getFields()) {
            // found key ?
            if (field.getKey() == keyOfField) {

                // try to set Score if done return true if it wasnt possible return false
                try {
                    // dont need throwed here cause its a Yatzi and u dont need the dices to calc Score
                    // so just give a empty Dice array in Function
                    int score = this.getScoreOfFieldWithDices(keyOfField, new Dice[5], yatziMultiplier);

                    field.setScore(score);

                    this.setYatziMultiplyFactorCounter(yatziMultiplier + 1);
                    return 1;
                }catch(Exception exception) {

                    System.out.println(exception.getMessage());

                    return 0;
                }
            }
        }
        // No field with this key was found, shouldnt be, selection doesnt work
        throw new FatalSystemError();
    }


    /**
     * checks if choosen Option is possible with current dices
     * @param keyOfField
     * @param throwed
     * @return
     */
    @Override
    boolean getIfOptionIsPossible(String keyOfField, Dice[] throwed) throws FatalSystemError {
        // interesting Problem that u cant use enum.toString()  in switch case. Seems to be a Java Runtime Thing .
        // Workaround with transfer String in right enum Field
        FieldsForStreaksScoreboard field = FieldsForStreaksScoreboard.valueOf(keyOfField);
        String failureMessage;
        switch (field) {
            case THREEOFAKIND:
                return this.isThreeOfAKindInDices(throwed);
            case FOUROFAKIND:
                return this.isFourOfAKindInDices(throwed);
            case FULLHOUSE:
                return this.isFullhouseInDices(throwed);
            case SMALLSTRAIGHT:
                return this.isSmallStraightInDices(throwed);
            case LARGESTRAIGHT:
                return this.isLargeStraightInDices(throwed);
            case YATZI:
                return this.isYatziInDices(throwed);
            case CHANCE:
                // dices are always right for Chance
                return true;
            default:
                // Should never go here cause keyField ever has to be one of the enum in the gameworkflow
                throw new FatalSystemError();
        }
    }

    /**
     *
     * @param keyOfField given field
     * @param throwed current dices
     * @return returns score to set for given field
     * @throws FatalSystemError
     */
    @Override
    int getScoreOfFieldWithDices(String keyOfField, Dice[] throwed, int yatziMultiplyFactor) throws FatalSystemError {
        // interesting Problem that u cant use enum.toString()  in switch case. Seems to be a Java Runtime Thing .
        // Workaround with transfer String in right enum Field
        FieldsForStreaksScoreboard field = FieldsForStreaksScoreboard.valueOf(keyOfField);
        switch (field) {
            case THREEOFAKIND:
            case FOUROFAKIND:
            case CHANCE:
                if (yatziMultiplyFactor > 0) {
                    return yatziMultiplyFactor * 50;
                }
                return this.sumAllDices(throwed);
            case FULLHOUSE:
                if (yatziMultiplyFactor > 0) {
                    return yatziMultiplyFactor * 50;
                }
                return 25;
            case SMALLSTRAIGHT:
                if (yatziMultiplyFactor > 0) {
                    return yatziMultiplyFactor * 50;
                }
                return 30;
            case LARGESTRAIGHT:
                if (yatziMultiplyFactor > 0) {
                    return yatziMultiplyFactor * 50;
                }
                return 40;
            case YATZI:
                // cant score multiply Yatzi on Yatzi field cause its already need to be set for this
                return 50;
            default:
                // Should never go here cause keyField ever has to be one of the enum in the gameworkflow
                throw new FatalSystemError();
        }
    }
    // comment in for Testing Issues
    /*
    public static void main(String[] args) throws FatalSystemError {
        StreaksScoreboard test = new StreaksScoreboard();
        Dice[] dices = new Dice[5];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(1);
        dices[2].setDicenumber(1);
        dices[3].setDicenumber(2);
        dices[4].setDicenumber(2);
        System.out.println("Should be true");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(2);
        dices[1].setDicenumber(2);
        dices[2].setDicenumber(5);
        dices[3].setDicenumber(5);
        dices[4].setDicenumber(5);
        System.out.println("Should be true");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(4);
        dices[2].setDicenumber(4);
        dices[3].setDicenumber(4);
        dices[4].setDicenumber(1);
        System.out.println("Should be true");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(2);
        dices[1].setDicenumber(5);
        dices[2].setDicenumber(2);
        dices[3].setDicenumber(2);
        dices[4].setDicenumber(5);
        System.out.println("Should be true");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(5);
        dices[1].setDicenumber(5);
        dices[2].setDicenumber(1);
        dices[3].setDicenumber(5);
        dices[4].setDicenumber(1);
        System.out.println("Should be true");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(1);
        dices[2].setDicenumber(1);
        dices[3].setDicenumber(2);
        dices[4].setDicenumber(2);
        System.out.println("Should be false");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(2);
        dices[2].setDicenumber(3);
        dices[3].setDicenumber(5);
        dices[4].setDicenumber(6);
        System.out.println("Should be false");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(2);
        dices[2].setDicenumber(3);
        dices[3].setDicenumber(5);
        dices[4].setDicenumber(6);
        System.out.println("Should be false");
        System.out.println(test.isFullhouseInDices(dices));
        dices[0].setDicenumber(1);
        dices[1].setDicenumber(1);
        dices[2].setDicenumber(1);
        dices[3].setDicenumber(2);
        dices[4].setDicenumber(4);
        System.out.println("Should be false");
        System.out.println(test.isFullhouseInDices(dices));
    }
    */


    /**
     *
     * @param dices
     * @return return if ThreeOfAKind is in given dives
     * @throws FatalSystemError
     */
    private boolean isThreeOfAKindInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // just need to check from first 3 places cause after that there cant be three of a kind anymore
        for (int i = 0; i < 3; i++) {
            int diceThatIsChecked = dices[i].getDicenumber();
            int countDices = 1;
            for(int j = i+1; j < dices.length; j++ ) {
                if(dices[j].getDicenumber() == diceThatIsChecked) {
                    countDices++;
                }
            }
            if (countDices >= 3) {
                return true;
            }

        }

        // no combination of 3 times the same dicenumber was found so return false
        return false;
    }

    /**
     *
     * @param dices
     * @return if ThreeOfAKind is in dices
     * @throws FatalSystemError
     */
    private boolean isFourOfAKindInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // for this check u need to check if u get the number that is not in the four of a kind or not
        // so u only need to go two times through the dices
        for (int i = 0; i < 2; i++) {
            int diceThatIsChecked = dices[i].getDicenumber();
            int countDices = 1;
            for(int j = i+1; j < dices.length; j++ ) {
                if(dices[j].getDicenumber() == diceThatIsChecked) {
                    countDices++;
                }
            }
            if (countDices >= 4) {
                return true;
            }
        }

        // not combination of 4 times the same dicenumber was found so return false
        return false;
    }

    /**
     *
     * @param dices
     * @return return if Fullhouse is in given dives
     * @throws FatalSystemError
     */
    private boolean isFullhouseInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // for Full House there need to be three of a Kind in Dices so copy that code plus information which dicenumber is the THREEOFAKIND
        boolean isThreeOfAKind = false;
        int diceNumberOfThreeOfAKind = -1;
        for (int i = 0; i < 3; i++) {
            int diceThatIsChecked = dices[i].getDicenumber();
            int countDices = 1;
            for(int j = i+1; j < dices.length; j++ ) {
                if(dices[j].getDicenumber() == diceThatIsChecked) {
                    countDices++;
                }
            }
            if (countDices >= 3) {
                isThreeOfAKind = true;
                diceNumberOfThreeOfAKind = diceThatIsChecked;
                // if three of kind is found dont need to search further
                break;
            }

        }


        if (isThreeOfAKind) {
            // here we only need to find the two other dices which are not part of the THREEOFAKIND an check if they have the same dicenumber
            int firstOtherDiceNumber = -1;
            for (int i = 0; i < dices.length; i++) {
                // if a otherDiceNumber is found and its the first time its found, save it
                if ((dices[i].getDicenumber() != diceNumberOfThreeOfAKind) && (firstOtherDiceNumber == -1)) {
                    firstOtherDiceNumber = dices[i].getDicenumber();
                }
                // if a otherDiceNumber is found and its NOT the first time its found, check if both are the same
                else if ((dices[i].getDicenumber() != diceNumberOfThreeOfAKind) && (firstOtherDiceNumber != -1)) {
                    // if they are the same its a full house otherwise not
                    if (firstOtherDiceNumber == dices[i].getDicenumber()) {
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }

        // no TRHEEOFAKIND in dices
        return false;
    }

    /**
     *
     * @param dices
     * @return return if Small Straight is in given dives
     * @throws FatalSystemError
     */
    private boolean isSmallStraightInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // for every Straigt there need to be a three or a four so check that to gain speed cause its a fast check
        if(!isNumberInDices(3, dices)){
            return false;
        }
        if(!isNumberInDices(4, dices)){
            return false;
        }


        // before checking sort them to make it easier
        int[] sortedNumberArray = this.sortDiceArrayAndReturnAsIntegerArray(dices);

        // now remove doubles to make it easier and only have 3 options
        int[] numberArraySortedandWithoutDoubles = this.removeDoubles(sortedNumberArray);
        // if length is 4 just check if its a small straight by going through
        if (numberArraySortedandWithoutDoubles.length == 4) {
            // after knowing its length its easy to check cause its just one possible small straight in 4 dices
            for (int i = 1; i < numberArraySortedandWithoutDoubles.length; i++) {
                if ( !(numberArraySortedandWithoutDoubles[i]-1 == numberArraySortedandWithoutDoubles[i-1]) ) {
                    return false;
                }
            }

            // its a small straight here so return true
            return true;
        }

        // if length is still 5
        // search for small straight, there are 2 possible small streets
        // so the straight can be on 2 places
        // indices 0-3, 1-4
        // first for loop are the 2 possibilities
        // second for loop is the check if there really is a straight
        for (int i = 0; i < 2; i++) {
            // first suggest we found it on this possibility, if not it will be set to false on the second loop
            int counter = 0; // count increasings by 1
            for (int j = i+1; j < numberArraySortedandWithoutDoubles.length; j++) {
                // to be a street next number has to be +1 one to previous
                if (!((numberArraySortedandWithoutDoubles[j] - 1) == (numberArraySortedandWithoutDoubles[j - 1]))) {
                    // if its not a straight check the other possibility
                    break;
                } else {
                    counter++;
                }
            }
            // if foundStraight wasnt set to false it is one and we can return true
            // bigger than 3 cause large straight is also a small straight
            if (counter >= 3) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param dices
     * @return return if Large Straight is in given dives
     * @throws FatalSystemError
     */
    private boolean isLargeStraightInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // before checking sort them to make it easier
        int[] numberArray = this.sortDiceArrayAndReturnAsIntegerArray(dices);

        // after sorting its easy to check cause its just one possible large straight in 5 dices
        for (int i = 1; i < numberArray.length; i++) {
            if ( !(numberArray[i]-1 == numberArray[i-1]) ) {
                return false;
            }
        }

        // if we are here Straight is confirmed
        return true;
    }

    /**
     *
     * @param dices
     * @return return if Yatzi is in given dives
     * @throws FatalSystemError
     */
    private boolean isYatziInDices(Dice[] dices) throws FatalSystemError {
        if (dices.length != 5) {
            // length should ever be 5 (5 dices)
            throw new FatalSystemError();
        }

        // all dices has to be same dicenumber
        int dicenumber = dices[0].getDicenumber();

        for (int i = 1; i < dices.length; i++) {
            // if there is only one other there is no yatzi
            if (!(dices[i].getDicenumber() == dicenumber)) {
                return false;
            }
        }

        return true;
    }

    /**
     * sort a Dice Array and return as IntegerArray cause its easier to work witth
     * @param dices given divces
     * @return sorted Dice Array as Integer Array
     */
    private int[] sortDiceArrayAndReturnAsIntegerArray(Dice[] dices) {
        int[] numberArray = new int[5];
        // fill it
        for (int i = 0; i < dices.length; i++) {
            numberArray[i] = dices[i].getDicenumber();
        }

        // sort it (TODO maybe with own Quicksort Algorithm)
        Arrays.sort(numberArray);

        return numberArray;
    }

    /**
     * removes doubles from given array
     * @param arr given array
     * @return new Array without doubles
     */
    private int[] removeDoubles(int[] arr) {
        ArrayList<Integer> newArr = new ArrayList<>();
        for (int i=0; i < arr.length-1; i++){
            if (arr[i] != arr[i+1]){
                newArr.add(arr[i]);
            }
        }
        newArr.add(arr[arr.length-1]);

        // arraylist to array TODO put that in a function
        int[] ret = new int[newArr.size()];
        for (int i = 0; i < newArr.size(); i++) {
            ret[i] = newArr.get(i);
        }

        return ret;
    }

    /**
     * sums all given dices
     * @param dices given dices
     * @return sum
     */
    private int sumAllDices(Dice[] dices) {
        int sum = 0;
        for (Dice dice: dices) {
            sum += dice.getDicenumber();
        }
        return sum;
    }
}