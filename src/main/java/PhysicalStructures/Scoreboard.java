package PhysicalStructures;

import Database.Lyrics;
import Exceptions.FatalSystemError;
import Structures.Field;
import Structures.FieldsForStreaksScoreboard;

public abstract class Scoreboard {
    private Field[] fields;

    // if yatzi must be multiplied its starts with *2
    private int yatziMultiplyFactorCounter = 2;

    public Scoreboard(Field[] pFields) {
        this.fields = pFields;
    }

    public Field[] getFields() {
        return fields;
    }

    /**
     * set a score on Scoreboard
     * @param keyOfField target Field
     * @param throwed dices that was thrown
     * @return returns if set score was succeded 0 for false 1 for true multipleYatzi for Yatzi Case
     */
    public int setAScore(String keyOfField, Dice[] throwed) throws FatalSystemError {
        // find field with right key
        for(Field field: this.fields) {
            // found key ?
            if (field.getKey() == keyOfField) {

                // if score can be set with this dices
                if (!this.getIfOptionIsPossible(keyOfField, throwed)) {
                    System.out.println(Lyrics.DICES_WRONG);
                    return 0;
                }

                // try to set Score if done return true if it wasnt possible return false
                try {
                    int score = this.getScoreOfFieldWithDices(keyOfField, throwed, 0);

                    field.setScore(score);

                    return 1;
                }catch(Exception exception) {
                    // for Yatzi we need a check here, if it was set but is possible user can set in on antoher field so
                    // so if Yatzi and exception is Score already set and NOT crossed because u cant score a yatzi then anymore
                    if ((keyOfField.equals(FieldsForStreaksScoreboard.YATZI.toString()))
                            && (exception.getMessage().equals(Lyrics.FIELD_SCORE_IS_ALREADY_SET)) ) {
                        return yatziMultiplyFactorCounter;
                    }
                    System.out.println(exception.getMessage());
                    return 0;
                }
            }
        }
        // No field with this key was found, shouldnt be, selection doesnt work
        throw new FatalSystemError();
    }

    /**
     * Method to cross out a field
     * @param keyOfField which field
     * @return if crossOut worked
     * @throws FatalSystemError
     */
    public boolean crossOut(String keyOfField) throws FatalSystemError {
        // find field with right key
        for(Field field: this.fields) {
            // found key ?
            if (field.getKey() == keyOfField) {
                return field.crossOut();
            }
        }
        // No field with this key was found, shouldnt be, selection doesnt work
        throw new FatalSystemError();
    }

    public void setYatziMultiplyFactorCounter(int yatziMultiplyFactorCounter) {
        this.yatziMultiplyFactorCounter = yatziMultiplyFactorCounter;
    }

    // need to use String Parameter here cause Different Scoreboards use different enums (maybe solve that Problem better)
    abstract boolean getIfOptionIsPossible(String keyOfField, Dice[] throwed) throws FatalSystemError;

    // need to use String Parameter here cause Different Scoreboards use different enums (maybe solve that Problem better)
    abstract int getScoreOfFieldWithDices(String keyOfField, Dice[] throwed, int multipleYatzis) throws FatalSystemError;


    /**
     * sums all scores
     * @return sum
     */
    public int getSumOfScoresFromAllFields() {
        int sum = 0;

        for (Field field: this.fields){
            sum += field.getScore();
        }

        return sum;
    }

    /**
     *
     * @param scoreboard
     * @return Scoreboard in Stringformat for Overview
     */
    public String toString(String scoreboard) {
        StringBuilder sb = new StringBuilder();
        sb.append(scoreboard + ": \n");
        for (Field field: this.getFields()) {
            sb.append(field.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * returns true if number is at least on a one dice
     * @param number searching number
     * @param dices dices with numbers on
     * @return true if number was found on one dice
     */
    protected boolean isNumberInDices(int number, Dice[] dices) {
        for (Dice dice: dices) {
            if (dice.getDicenumber() == number) {
                return true;
            }
        }
        return false;
    }
    /**
     * evaluates failure Message and returns if its a failed Message or not  and prints it if it is one
     * @param failureMessage
     * @return if its failed or not
     */
    protected boolean evaluateFailureMessage(String failureMessage) {
        if (failureMessage.isEmpty()) {
            return true;
        }

        System.out.println(failureMessage);
        return false;
    }
}