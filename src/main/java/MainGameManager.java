import Database.Lyrics;
import Database.Settings;
import Exceptions.FatalSystemError;
import PhysicalStructures.*;
import Structures.FieldsForNumberScoreboard;
import Structures.FieldsForStreaksScoreboard;
import Structures.UsefullFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MainGameManager {
    public static void main(String[] args) throws FatalSystemError {
        // start new Game
        MainGameManager game = new MainGameManager();

        // Choose number of Players
        int numbersOfPlayers = game.DialogWithNumberUserResponse(Lyrics.getOptionsForNumberOfPlayers(), 1, Settings.POSSIBLE_NUMBER_OF_PLAYERS);

        System.out.println(Lyrics.getMessageNumberOfPlayers(numbersOfPlayers));

        // Get Player Names and create Player
        Player[] players = new Player[numbersOfPlayers];
        // Set a name for every Player correct
        for (int i = 0; i < numbersOfPlayers; i++) {
            players[i] = new Player(
                    game.DialogWithStringUserResponse(
                            new String[]{Lyrics.getMessageForGetPlayername(i+1)}
                    )
            );
        }

        // Main Game begins here
        // every player plays turn till last Player in row has finished the game
        while (!players[players.length-1].gameDone()) {


            // every Player plays one Turn in one Round, Player 1 starts the Round
            for (int i = 0; i < players.length; i++) {


                // One Turn
                System.out.println(Lyrics.getPlayerXTurn(players[i].getName()));

                // 3 mal würfeln
                int counter = 3;

                // solange nicht gewürfelt wurde ist der Spieler an der Reihe, wenn gewürfelt wurde muss darf er nur noch 2 mal würfeln
                int optionChoosen = -1;
                while (optionChoosen != 1) {
                    optionChoosen = game.DialogWithNumberUserResponse(Lyrics.getOptionsForThrowingDices(), 1, 7);
                    switch (optionChoosen) {
                        case 1:
                            // Throw Dices
                            ArrayList<Integer> thrown = players[i].getDiceCup().simulateThrowingAllNotSafedDices();
                            System.out.println(Lyrics.getMessageForDicesWillBeThrown(thrown.size()));
                            System.out.println(Lyrics.getMessageForDicesWasThrown(thrown));

                            // here the dices are thrown so in this turn the player can throw them one less
                            counter--;
                            if(counter != 0) {
                                optionChoosen = -1;
                            }
                            break;
                        case 2:
                            // Shake Dices
                            System.out.println(Lyrics.SHAKE_DICES);
                            break;
                        case 3:
                            // See safed Dices

                            if(players[i].getDiceCup().getSafedDices().isEmpty()) {
                                System.out.println(Lyrics.NO_SAVED_DICES);
                                break;
                            }

                            System.out.println(Lyrics.SAVED_DICES + UsefullFunctions.
                                    ArrayListIntToString(players[i].getDiceCup().getSafedDices()));

                            break;
                        case 4:
                            // See all Dices
                            if (players[i].getDiceCup().getAllDices().length == 0) {
                                System.out.println(Lyrics.NO_DICES_THROWN);
                                break;
                            }

                            System.out.println(Lyrics.ALL_DICES + Arrays.toString(players[i].getDiceCup().getAllDices()));

                            break;
                        case 5:
                            // if dices hasnt been thrown u cant safe them of course
                            if (players[i].getDiceCup().getAllDices().length == 0) {
                                System.out.println(Lyrics.NO_DICES_THROWN);
                                break;
                            }

                            // Safe Dices
                            boolean correctInput = false;

                            while (!correctInput) {
                                String input = game.DialogWithStringUserResponse(new String[]{Lyrics.getMessageForSaveDices()});
                                ArrayList<Integer> splittetAsInteger = new ArrayList<>();

                                // if user want to delete current saved ones
                                if (input.equals("del")) {
                                    players[i].getDiceCup().deleteSafedDices();
                                    break;

                                } else {
                                    // Split input on comma to get numbers of dices to safe
                                    try {
                                        String[] splitet = input.split(",");

                                        for (String str: splitet) {
                                            splittetAsInteger.add(Integer.parseInt(str));
                                        }

                                        // if u are here there wasnt any errors and the input is correct
                                        correctInput = true;
                                        // Transform ArrayList to Array
                                        int[] arrayInt = UsefullFunctions.makeArrayListToArray(splittetAsInteger);

                                        players[i].getDiceCup().safeDices(arrayInt);
                                    } catch (Exception e) {
                                        System.out.println(Lyrics.WRONG_INPUT);
                                    }
                                }
                            }

                            System.out.println(Lyrics.NEW_SELECTION_OF_SAFED_DIVES);

                            break;
                        case 6:
                            // Skip to Register
                            if (players[i].getDiceCup().getAllDices().length == 0) {
                                // dices wasnt thrown yet so it cant be skipped
                                System.out.println(Lyrics.NO_DICES_THROWN);
                            } else {
                                optionChoosen = 1;
                                Arrays.toString(players[i].getDiceCup().getAllDices());
                            }
                            break;
                        case 7:
                            // See current Scoreboard
                            System.out.println(players[i].getNumbersScoreboard().toString(Lyrics.NAME_OF_FIRST_SCOREBOARD));
                            System.out.println(players[i].getStreaksScoreboard().toString(Lyrics.NAME_OF_SECOND_SCOREBOARD));
                            break;
                        default:
                            // Only goes here if DialogWithNumberUserResponse doesnt work
                            throw new FatalSystemError();

                    }
                }

                // after 3 throws register

                boolean somethingWasRegisterd = false;
                while(!somethingWasRegisterd) {
                    int input = game.DialogWithNumberUserResponse(Lyrics.getOptionsForChooseScoreboard(), 1, 6);
                    int worked;
                    switch (input) {
                        case 1:
                            int getFieldNumberForNumberScoreboard = game.DialogWithNumberUserResponse(Lyrics.getOptionsForRegisterScoreOnNumberScoreboard(), 1 ,7);

                            switch (getFieldNumberForNumberScoreboard) {
                                case 1:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.ONES.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 2:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.TWOS.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 3:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.THREES.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 4:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.FOURS.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 5:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.FIVES.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 6:
                                    worked = players[i].getNumbersScoreboard().setAScore(FieldsForNumberScoreboard.SIXS.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 7:
                                    // Going Back and choose other Scoreboard so nothing was registerd
                                    break;
                                default:
                                    // Only goes here if DialogWithNumberUserResponse doesnt work
                                    throw new FatalSystemError();
                            }
                            break;
                        case 2:
                            int getFieldNumberForStreaksScoreBoard = game.DialogWithNumberUserResponse(Lyrics.getOptionsForRegisterScoreOnStreakScoreboard(), 1 ,8);

                            switch (getFieldNumberForStreaksScoreBoard) {
                                case 1:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.THREEOFAKIND.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 2:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.FOUROFAKIND.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 3:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.FULLHOUSE.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 4:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.SMALLSTRAIGHT.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 5:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.LARGESTRAIGHT.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 6:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.YATZI.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);

                                    } else if (worked >= 2) {
                                        // when we are here worked is going to be the Yatzi Count
                                        int yatziMultiplier = worked;
                                        int secondInput = game.DialogWithNumberUserResponse(Lyrics.getOptionsForRegisterYatziMultipleTimes(), 1, 7);
                                        switch (secondInput) {
                                            case 1:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.THREEOFAKIND.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 2:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.FOUROFAKIND.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 3:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.FULLHOUSE.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 4:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.SMALLSTRAIGHT.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 5:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.LARGESTRAIGHT.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 6:
                                                worked = players[i].getStreaksScoreboard().setYatziScoreOnOtherFields(FieldsForStreaksScoreboard.CHANCE.toString()
                                                        , yatziMultiplier);
                                                if (worked == 1) {
                                                    somethingWasRegisterd = true;
                                                    System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                                }
                                                break;
                                            case 7:
                                                // Going Back and put yatzi dices on other field, maybe cause streak socreboard is already fully set
                                                break;
                                            default:
                                                // Only goes here if DialogWithNumberUserResponse doesnt work
                                                throw new FatalSystemError();
                                        }
                                    }
                                    break;
                                case 7:
                                    worked = players[i].getStreaksScoreboard().setAScore(FieldsForStreaksScoreboard.CHANCE.toString()
                                            , UsefullFunctions.makeIntegerArrayToDiceArray(players[i].getDiceCup().getAllDices()));
                                    if (worked == 1) {
                                        somethingWasRegisterd = true;
                                        System.out.println(Lyrics.REGISTRATION_SUCCESSFULL);
                                    }
                                    break;
                                case 8:
                                    // Going Back and choose other Scoreboard so nothing was registerd
                                    break;
                                default:
                                    // Only goes here if DialogWithNumberUserResponse doesnt work
                                    throw new FatalSystemError();
                            }
                            break;
                        case 3:
                            int getFieldNumberForCrossingOutOnFirst = game.DialogWithNumberUserResponse(Lyrics.getOptionsForCrossOutOnNumberScoreboard(), 1 ,7);

                            switch (getFieldNumberForCrossingOutOnFirst) {
                                case 1:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.ONES.toString());
                                    break;
                                case 2:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.TWOS.toString());
                                    break;
                                case 3:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.THREES.toString());
                                    break;
                                case 4:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.FOURS.toString());
                                    break;
                                case 5:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.FIVES.toString());
                                    break;
                                case 6:
                                    somethingWasRegisterd = game.crossOutFieldOnNumberScoreboard(players[i], FieldsForNumberScoreboard.SIXS.toString());
                                    break;
                                case 7:
                                    // Going Back and choose other Scoreboard so nothing was registerd
                                    break;
                                default:
                                    // Only goes here if DialogWithNumberUserResponse doesnt work
                                    throw new FatalSystemError();
                            }

                            break;
                        case 4:
                            int getFieldNumberForCrossingOutOnSecond = game.DialogWithNumberUserResponse(Lyrics.getOptionsForCrossOutOnStreakScoreboard(), 1 ,8);
                            switch (getFieldNumberForCrossingOutOnSecond) {
                                case 1:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.THREEOFAKIND.toString());
                                    break;
                                case 2:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.FOUROFAKIND.toString());
                                    break;
                                case 3:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.FULLHOUSE.toString());
                                    break;
                                case 4:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.SMALLSTRAIGHT.toString());
                                    break;
                                case 5:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.LARGESTRAIGHT.toString());
                                    break;
                                case 6:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.YATZI.toString());
                                    break;
                                case 7:
                                    somethingWasRegisterd = game.crossOutFieldOnStreakScoreboard(players[i], FieldsForStreaksScoreboard.CHANCE.toString());
                                    break;
                                case 8:
                                    // Going Back and choose other Scoreboard so nothing was registerd
                                    break;
                                default:
                                    // Only goes here if DialogWithNumberUserResponse doesnt work
                                    throw new FatalSystemError();
                            }
                            break;
                        case 5:
                            // See all Dices
                            if (players[i].getDiceCup().getAllDices().length == 0) {
                                System.out.println(Lyrics.NO_DICES_THROWN);
                                break;
                            }

                            System.out.println(Lyrics.ALL_DICES + Arrays.toString(players[i].getDiceCup().getAllDices()));

                            break;
                        case 6:
                            System.out.println(players[i].getNumbersScoreboard().toString(Lyrics.NAME_OF_FIRST_SCOREBOARD));
                            System.out.println(players[i].getStreaksScoreboard().toString(Lyrics.NAME_OF_SECOND_SCOREBOARD));
                            break;
                        default:
                            // Only goes here if DialogWithNumberUserResponse doesnt work
                            throw new FatalSystemError();
                    }
                }

                // Reset Values for next turn
                boolean checkIfResetWorked = players[i].getDiceCup().resetValues();
                if (!checkIfResetWorked) {
                    throw new FatalSystemError();
                }
            }

        }

        // Game is done cause every Player filled every Field
        // so calculate Points here and print who has won
        Integer[] scores = new Integer[players.length];
        boolean[] winners = new boolean[players.length];

        // calc score
        for (int i = 0; i < players.length; i++) {
            scores[i] = players[i].getFinalScore();
        }
        // get winners
        int max = Collections.max(Arrays.asList(scores));
        for(int i = 0; i < players.length; i++) {
            if (scores[i] == max) {
                winners[i] = true;
            }
        }

        // Print Winners
        for (int i = 0; i < winners.length; i++) {
            if (winners[i]) {
                System.out.println(players[i].getName() + ": " + scores[i] + " WINNER :)");
            }else {
                System.out.println(players[i].getName() + ": " + scores[i]);
            }
        }
    }

    private boolean crossOutFieldOnNumberScoreboard(Player player, String field) throws FatalSystemError {
        if (player.getNumbersScoreboard().crossOut(field)) {
            System.out.println(Lyrics.CROSSOUT_SUCCESSFULL);
            return true;
        }

        return false;
    }

    private boolean crossOutFieldOnStreakScoreboard(Player player, String field) throws FatalSystemError {
        if (player.getStreaksScoreboard().crossOut(field)) {
            System.out.println(Lyrics.CROSSOUT_SUCCESSFULL);
            return true;
        }

        return false;
    }

    private int DialogWithNumberUserResponse(String[] options, int rangeOfOptionsMin, int rangeOfOptionsMax) {
        int input = -1;

        while (input == -1) {
            for (String line: options) {
                System.out.println(line);
            }

            Scanner scannerVariable = new Scanner(System.in);
            try {
                input = scannerVariable.nextInt();
                // if not in range
                if ( !(input >= rangeOfOptionsMin && input <= rangeOfOptionsMax) ) {
                    // set input to -1 to continue user input
                    input = -1;
                    System.out.println(Lyrics.getMessageChooseAnAvailableOptionInfo());
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("---------------INFO---------------");
                System.out.println("Please enter numbers only !");
                System.out.println();
            }

        }

        return input;
    }

    private String DialogWithStringUserResponse(String[] options) {
        String input = "";

        while (input == "") {
            for (String line: options) {
                System.out.println(line);
            }

            Scanner scannerVariable = new Scanner(System.in);
            try {
                input = scannerVariable.nextLine();
            } catch (Exception e) {
                System.out.println();
                System.out.println("---------------INFO---------------");
                System.out.println("Please enter a text !");
                System.out.println();
            }

        }

        return input;
    }
}