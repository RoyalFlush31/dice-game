package Database;

import java.util.ArrayList;

/**
 *  just a database to save all text and make it possible to change it fast
 */
public class Lyrics {
    public static final String CHOOSE_PLAYER_NUMBER = "Please select the number of players:";
    public static final String INFO_FOR_CHOOSING_OPTIONS = "Please select one of the following options by entering the number in front of it.";
    public static final String SAVED_DICES = "These are ur current saved Dices: ";
    public static final String NO_SAVED_DICES = "Currently u have no saved Dices.";
    public static final String ALL_DICES = "These are ur current Dices (saved and unsaved): \n";
    public static final String NO_DICES_THROWN = "U havent thrown the dices yet.";
    public static final String WRONG_INPUT = "The Input is wrong. Please use right Format !";
    public static final String SAVE_DIVES = "test";
    public static final String SHAKE_DICES = "Dices was shaked! Good Luck! ";
    public static final String NEW_SELECTION_OF_SAFED_DIVES = "Input of new Selection for Safed Dices was Saved.";
    public static final String FIELD_WAS_NOT_FOUND = "Field was not found !";
    public static final String FIELD_IS_CROSSED_OUT = "Field has already been crossed out !";
    public static final String FIELD_SCORE_IS_ALREADY_SET = "Score of this Field has already been set !";
    public static final String NAME_OF_FIRST_SCOREBOARD = "Numberscoreboard";
    public static final String NAME_OF_SECOND_SCOREBOARD = "Streakscoreboard";
    public static final String REGISTRATION_SUCCESSFULL = "Registration was successfull.";
    public static final String CROSSOUT_SUCCESSFULL = "Crossout was successfull.";
    public static final String DICES_WRONG = "You cant set this field with ur current Dices !";



    // Selection Options
    public static String[] getOptionsForNumberOfPlayers() {
        ArrayList<String> options = new ArrayList<>();

        options.add(Lyrics.CHOOSE_PLAYER_NUMBER);
        options.add(Lyrics.INFO_FOR_CHOOSING_OPTIONS);

        for (int i = 0; i < Settings.POSSIBLE_NUMBER_OF_PLAYERS; i++){
            options.add("(" + (i+1) + ") " + (i+1) + " Players" );
        }

        String[] ret = new String[options.size()];

        for (int i = 0; i < options.size() ; i++) {
            ret[i] = options.get(i);
        }

        return ret;
    }

    public static String[] getOptionsForThrowingDices() {
        return new String[]{
                "(1) THROW DICES ",
                "(2) SHAKE DICES",
                "(3) SEE SAFED DICES ",
                "(4) SEE ALL DICES ",
                "(5) SELECT SAFED DICES OR DELETE SELECTION ",
                "(6) Skip to Register with current dices"
        };
    }

    public static String[] getOptionsForChooseScoreboard() {
        return new String[]{
                "(1) Register at " + NAME_OF_FIRST_SCOREBOARD,
                "(2) Register at " + NAME_OF_SECOND_SCOREBOARD,
                "(3) Cross out at " + NAME_OF_FIRST_SCOREBOARD,
                "(4) Cross out at " + NAME_OF_SECOND_SCOREBOARD,
                "(5) SEE ALL DICES",
                "(6) See current Scoreboards"

        };
    }

    public static String[] getOptionsForRegisterScoreOnNumberScoreboard() {
        return new String[]{
                "(1) Register at ONES",
                "(2) Register at TWOS",
                "(3) Register at THREES",
                "(4) Register at FOURS",
                "(5) Register at FIVES",
                "(6) Register at SIXES",
                "(7) Go Back"
        };
    }

    public static String[] getOptionsForRegisterScoreOnStreakScoreboard() {
        return new String[]{
                "(1) Register at THREEOFAKIND",
                "(2) Register at FOUROFAKIND",
                "(3) Register at FULLHOUSE",
                "(4) Register at SMALLSTRAIGHT",
                "(5) Register at LARGESTRAIGHT",
                "(6) Register at YATZI",
                "(7) Register at Chance",
                "(8) Go Back"
        };
    }

    public static String[] getOptionsForCrossOutOnNumberScoreboard() {
        return new String[]{
                "(1) CROSS OUT ONES",
                "(2) CROSS OUT TWOS",
                "(3) CROSS OUT THREES",
                "(4) CROSS OUT FOURS",
                "(5) CROSS OUT FIVES",
                "(6) CROSS OUT SIXES",
                "(7) Go Back"
        };
    }

    public static String[] getOptionsForCrossOutOnStreakScoreboard() {
        return new String[]{
                "(1) CROSS OUT THREEOFAKIND",
                "(2) CROSS OUT FOUROFAKIND",
                "(3) CROSS OUT FULLHOUSE",
                "(4) CROSS OUT SMALLSTRAIGHT",
                "(5) CROSS OUT LARGESTRAIGHT",
                "(6) CROSS OUT YATZI",
                "(7) CROSS OUT Chance",
                "(8) Go Back"
        };
    }

    public static String[] getOptionsForRegisterYatziMultipleTimes() {
        return new String[]{
                "(1) Register Yatzi Points at THREEOFAKIND",
                "(2) Register Yatzi Points at FOUROFAKIND",
                "(3) Register Yatzi Points at FULLHOUSE",
                "(4) Register Yatzi Points at SMALLSTRAIGHT",
                "(5) Register Yatzi Points at LARGESTRAIGHT",
                "(6) Register Yatzi Points at Chance",
                "(7) Go Back"
        };
    }

    // Messages
    public static String getMessageNumberOfPlayers(int numbersOfPlayers) {
        return "" + numbersOfPlayers + " Players are in the game.";
    }

    public static String getMessageChooseAnAvailableOptionInfo() {
        return "\n" +
                "---------------INFO---------------\n" +
                "Enter a valid number of an option!" +
                "\n";
    }

    public static String getMessageForGetPlayername(int spielernummer){
        return  "Please enter a name for player " + spielernummer + ".";
    }

    public static String getPlayerXTurn(String playername) {
        return "It's " + playername + " 's turn.";
    }

    public static String getMessageForDicesWillBeThrown(int numberOfDices) {
        return "Throwing " + numberOfDices + " Dices ...";
    }


    public static String getMessageForDicesWasThrown(ArrayList<Integer> result) {
        return "Throwed Dices are:\n" + result;
    }

    public static String getMessageForSaveDices() {
        return "To save Dices type in all Dicenumbers u want to safe and seperate them with a comma:\n" +
                "For Example:  1,3,1\n" +
                "This will Safe 3 Dices. Two with dicenumber 1 and one with dicenumber 3\n" +
                "To delete current Selection of Saved Dices type in 'del' ";
    }

    public static String getMessageForCantSetScoreOnFieldWithThisCombination(String key) {
        return "Cant score on Field " + key + " with current dices !";
    }

}