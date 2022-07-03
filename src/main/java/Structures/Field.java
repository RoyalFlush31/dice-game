package Structures;

import Database.Lyrics;
import Exceptions.FieldIsCrossedOutException;
import Exceptions.ScoreAlreadySetException;

public class Field {
    private String key;
    private int score;
    private boolean crossedOut;

    public Field(String pKey) {
        this.key = pKey;
        // Every Field has a score of 0 from Beginning
        this.score = 0;
        // Every Field is not Crossed Out from Beginning
        this.crossedOut = false;
    }

    // just need Getter for Key cause it never change
    public String getKey() {
        return key;
    }

    public int getScore() {
        return score;
    }

    /**
     * set Score for Field
     * @param score score that should be set
     * @return returns if setScore was suceeded
     * @throws Exception if Field is already set or crossed out
     */
    public boolean setScore(int score) throws Exception {
        // Score can just be set once
        if ( (this.score == 0) && (!this.crossedOut) ) {
            this.score = score;
            return true;
        } else if(this.crossedOut) {
            throw new FieldIsCrossedOutException(Lyrics.FIELD_IS_CROSSED_OUT);
        } else {
            throw new ScoreAlreadySetException(Lyrics.FIELD_SCORE_IS_ALREADY_SET);
        }
    }

    public boolean isCrossedOut() {
        return crossedOut;
    }

    public boolean isEmpty() {
        if(this.score == 0 && !this.crossedOut) {
            return true;
        }
        return false;
    }

    public boolean crossOut() {
        if (this.crossedOut) {
            // Field was already crossed out
            System.out.println(Lyrics.FIELD_IS_CROSSED_OUT);
            return false;
        }

        if(!(this.score == 0)) {
            // Field has a score so it cant be crossed out
            System.out.println(Lyrics.FIELD_SCORE_IS_ALREADY_SET);
            return false;
        }

        this.crossedOut = true;
        return true;
    }

    @Override
    public String toString() {
        String entry;
        if (this.crossedOut) {
            entry = "CROSSED";
        }else if (this.score == 0) {
            entry = "NOT SET";
        } else {
            entry = "" + score;
        }
        return this.key + ":    " + entry;
    }
}
