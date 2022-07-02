package PhysicalStructures;

import Exceptions.FatalSystemError;
import Structures.Field;
import Structures.FieldsForNumberScoreboard;

public class NumbersScoreboard extends Scoreboard {
    private final int BONUS_BARRIER = 63;
    private final int BONUS = 35;

    public NumbersScoreboard() {
        // NumberScoreboard has 6 Fields
        super(new Field[6]);

        //Filling Field Array with right fields for NumberScoreboard
        this.getFields()[0] = new Field(FieldsForNumberScoreboard.ONES.toString());
        this.getFields()[1] = new Field(FieldsForNumberScoreboard.TWOS.toString());
        this.getFields()[2] = new Field(FieldsForNumberScoreboard.THREES.toString());
        this.getFields()[3] = new Field(FieldsForNumberScoreboard.FOURS.toString());
        this.getFields()[4] = new Field(FieldsForNumberScoreboard.FIVES.toString());
        this.getFields()[5] = new Field(FieldsForNumberScoreboard.SIXS.toString());
    }

    /**
     * calculates Finalscore for first Part of Scorboard
     * @return final Scoreboard
     */
    public int getFinalPointsForNumberScoreboard() {
        if (getBonus()) {
            return this.getSumOfScoresFromAllFields() + this.BONUS;
        } else {
            return this.getSumOfScoresFromAllFields();
        }
    }

    @Override
    boolean getIfOptionIsPossible(String keyOfField, Dice[] throwed) throws FatalSystemError {
        // interesting Problem that u cant use enum.toString()  in switch case. Seems to be a Java Runtime Thing .
        // Workaround with transfer String in right enum Field
        FieldsForNumberScoreboard field = FieldsForNumberScoreboard.valueOf(keyOfField);
        String failureMessage;
        switch (field) {
            case ONES:
                return isNumberInDices(1, throwed);
            case TWOS:
                return isNumberInDices(2, throwed);
            case THREES:
                return isNumberInDices(3, throwed);
            case FOURS:
                return isNumberInDices(4, throwed);
            case FIVES:
                return isNumberInDices(5, throwed);
            case SIXS:
                return isNumberInDices(6, throwed);
            default:
                System.out.println(field);
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
    int getScoreOfFieldWithDices(String keyOfField, Dice[] throwed, int multipleYatzis) throws FatalSystemError {
        // dont need multipleYatzis in NumbersScoreboard cause its only can be scored on other Scoreboard

        // interesting Problem that u cant use enum.toString()  in switch case. Seems to be a Java Runtime Thing .
        // Workaround with transfer String in right enum Field
        FieldsForNumberScoreboard field = FieldsForNumberScoreboard.valueOf(keyOfField);
        // possibilty of scoring in this field was probably checked before but is also not important for calculating
        switch (field) {
            case ONES:
                return this.sumDicesWithGivenNumber(1, throwed);
            case TWOS:
                return this.sumDicesWithGivenNumber(2, throwed);
            case THREES:
                return this.sumDicesWithGivenNumber(3, throwed);
            case FOURS:
                return this.sumDicesWithGivenNumber(4, throwed);
            case FIVES:
                return this.sumDicesWithGivenNumber(5, throwed);
            case SIXS:
                return this.sumDicesWithGivenNumber(6, throwed);
            default:
                // Should never go here cause keyField ever has to be one of the enum in the gameworkflow
                throw new FatalSystemError();
        }
    }

    /**
     * check if Player will get a Bonus with current scores
     * @return if player get bonus or not
     */
    private boolean getBonus(){
        if (!(this.getSumOfScoresFromAllFields() >= this.BONUS_BARRIER)){
            return false;
        }

        return true;
    }

    private int sumDicesWithGivenNumber(int number, Dice[] dices) {
        int sum = 0;
        for (int i = 0; i < dices.length; i++) {
            if (dices[i].getDicenumber() == number) {
                sum += number;
            }
        }
        return sum;
    }

}