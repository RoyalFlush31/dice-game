package PhysicalStructures;

import Structures.Field;

public class Player {
    private NumbersScoreboard numbersScoreboard;
    private StreaksScoreboard streaksScoreboard;
    private DiceCup diceCup;
    private String name;

    public Player(String pName) {
        this.name = pName;
        this.numbersScoreboard = new NumbersScoreboard();
        this.streaksScoreboard = new StreaksScoreboard();
        this.diceCup = new DiceCup();
    }

    public String getName() {
        return name;
    }

    public NumbersScoreboard getNumbersScoreboard() {
        return numbersScoreboard;
    }

    public StreaksScoreboard getStreaksScoreboard() {
        return streaksScoreboard;
    }

    public DiceCup getDiceCup() {
        return diceCup;
    }

    /**
     * checks if every Field is filled
     * @return true if every field is filled
     */
    public boolean gameDone() {
        for (Field field: numbersScoreboard.getFields()) {
            // If field is empty
            if(field.isEmpty()) {
                return false;
            }
        }

        for (Field field: streaksScoreboard.getFields()) {
            // If field is empty
            if(field.isEmpty()) {
                return false;
            }
        }

        // if reached here no field from NumberScoreboard or StreakScoreboard is Empty
        return true;
    }

    /**
     *
     * @return Final Score for player
     */
    public int getFinalScore() {
        return this.numbersScoreboard.getFinalPointsForNumberScoreboard() + this.streaksScoreboard.getFinalScoreForStreaksScoreboard();
    }
}