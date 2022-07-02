package Structures;

import Database.Lyrics;
import PhysicalStructures.Dice;

import java.util.ArrayList;

public class UsefullFunctions {
    /**
     * Transform ArrayList to Array
     * @param arrayList arraylist that should be transformed
     * @return normal array
     */
    public static int[] makeArrayListToArray(ArrayList<Integer> arrayList) {
        int[] arrayInt = new int[arrayList.size()];
        for (int j = 0; j < arrayList.size(); j++){
            arrayInt[j] = arrayList.get(j);
        }
        return arrayInt;
    }

    /**
     * Transform array to Dice Array
     * @param array array that should be transformed
     * @return dice array
     */
    public static Dice[] makeIntegerArrayToDiceArray(int[] array) {
        Dice[] dices = new Dice[array.length];
        for (int i = 0; i < array.length; i++) {
            dices[i] = new Dice(array[i]);

        }
        return dices;
    }

    /**
     *
     * @param field
     * @param dicesRight
     * @return the right message for a fail or empty string if there isnt a problem
     */
    public static String getRightMessage(Field field, boolean dicesRight) {
        // if field hasnt been scored yet and isnt crossed out, then scoring is possible if dices are correct
        if ((field.getScore() == 0) && (!field.isCrossedOut())) {
            if (!dicesRight) {
                return Lyrics.getMessageForCantSetScoreOnFieldWithThisCombination(field.getKey());

            }
            return "";
        } else if (field.isCrossedOut()) {
            return Lyrics.FIELD_IS_CROSSED_OUT;
        } else {
            return Lyrics.FIELD_SCORE_IS_ALREADY_SET;
        }
    }

    public static String ArrayListIntToString (ArrayList<Integer> arrayList) {
        if (arrayList.isEmpty()){
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int number: arrayList) {
            stringBuilder.append(number);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}