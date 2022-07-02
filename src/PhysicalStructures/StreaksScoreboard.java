package PhysicalStructures;

import Exceptions.FatalSystemError;
import PhysicalStructures.Scoreboard;
import Structures.Field;
import Structures.FieldsForStreaksScoreboard;
import PhysicalStructures.Dice;

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


    /**
     *
     * @param dices
     * @return return if ThreeOfAKind is in given dives
     * @throws FatalSystemError
     */
    private boolean isThreeOfAKindInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }

    /**
     *
     * @param dices
     * @return if ThreeOfAKind is in dices
     * @throws FatalSystemError
     */
    private boolean isFourOfAKindInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }

    /**
     *
     * @param dices
     * @return return if Fullhouse is in given dives
     * @throws FatalSystemError
     */
    private boolean isFullhouseInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }

    /**
     *
     * @param dices
     * @return return if Small Straight is in given dives
     * @throws FatalSystemError
     */
    private boolean isSmallStraightInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }

    /**
     *
     * @param dices
     * @return return if Large Straight is in given dives
     * @throws FatalSystemError
     */
    private boolean isLargeStraightInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }

    /**
     *
     * @param dices
     * @return return if Yatzi is in given dives
     * @throws FatalSystemError
     */
    private boolean isYatziInDices(Dice[] dices) throws FatalSystemError {
        return true;
    }
}