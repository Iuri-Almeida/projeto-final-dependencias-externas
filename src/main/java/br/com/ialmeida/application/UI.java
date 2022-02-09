package br.com.ialmeida.application;

import br.com.ialmeida.entities.Rebel;
import br.com.ialmeida.enums.Race;
import br.com.ialmeida.sort.BubbleSort;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printTitle(Scanner sc) {
        System.out.println("Rebels Registration!\n");
        System.out.println("Press ENTER to start.");
        sc.nextLine();
    }

    public static void printRebelTitle(int n) {
        System.out.printf(ProgramConstants.TITLE_COLOR + "Rebel #%d\n\n" + ProgramConstants.RESET_COLOR, n + 1);
    }

    public static String readName(Scanner sc, String txt) {
        System.out.print(ProgramConstants.INPUT_COLOR + txt + ProgramConstants.RESET_COLOR);
        return sc.nextLine().trim();
    }

    public static int readAge(Scanner sc, String txt) {
        System.out.print(ProgramConstants.INPUT_COLOR + txt + ProgramConstants.RESET_COLOR);
        try {
            String n = sc.nextLine().trim();
            return Integer.parseInt(n);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading integer inputs.");
        }
    }

    public static Race readRace(Scanner sc, String txt) {
        System.out.println(ProgramConstants.INPUT_COLOR + txt + ProgramConstants.RESET_COLOR);
        for (Race race : Race.values()) {
            System.out.printf(ProgramConstants.OPTION_COLOR + "%d - %s\n" + ProgramConstants.RESET_COLOR, race.ordinal(), race.name());
        }

        System.out.print(ProgramConstants.INPUT_COLOR + "\nChoose: " + ProgramConstants.RESET_COLOR);

        try {
            int raceOrdinal = Integer.parseInt(sc.nextLine().trim());
            return Race.values()[raceOrdinal];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputMismatchException("Values must be 0, 1 or 2.");
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading integer inputs.");
        }
    }

    public static boolean wannaJoinUs(Scanner sc) {
        char answer;
        String msg = "";

        do {
            System.out.print(ProgramConstants.QUESTION_COLOR + msg + "\nWanna join us? [y/n] " + ProgramConstants.RESET_COLOR);
            answer = sc.next().trim().toLowerCase().charAt(0);
            sc.nextLine();
            msg = "\nSorry, we will only accept `y` or `n`. ";
        } while (answer != 'y' && answer != 'n');

        return answer == 'y';
    }

    public static void orderTheRebels(Scanner sc, HashMap<Integer, Rebel> rebels) {
        int userChoice = getUserChoice(sc);

        switch (userChoice) {
            case 0:
                BubbleSort.sortByName(rebels);
                break;
            case 1:
                BubbleSort.sortByAge(rebels);
                break;
            default:
                BubbleSort.sortByRace(rebels);
                break;
        }

        printOrderedRebels(rebels);
    }

    private static int getUserChoice(Scanner sc) {
        System.out.println(ProgramConstants.QUESTION_COLOR + "From which attribute the rebels should be listed?\n" + ProgramConstants.RESET_COLOR);

        System.out.println(ProgramConstants.OPTION_COLOR + "0 - Name");
        System.out.println("1 - Age");
        System.out.println("2 - Race" + ProgramConstants.RESET_COLOR);

        System.out.println();

        int answer;
        String msg = "";

        do {
            System.out.print(ProgramConstants.INPUT_COLOR + msg + "Choose: " + ProgramConstants.RESET_COLOR);
            char c = sc.nextLine().trim().charAt(0);

            try {
                answer = Integer.parseInt(String.valueOf(c));
            } catch (RuntimeException e) {
                answer = 3;
            }

            msg = "\nSorry, we will only accept 0, 1 or 2. ";
        } while (answer < 0 || answer > 2);

        return answer;
    }

    private static void printOrderedRebels(HashMap<Integer, Rebel> rebels) {
        System.out.println(ProgramConstants.FINISH_COLOR + "\nOrdered Rebels\n" + ProgramConstants.RESET_COLOR);

        for (int i = 0; i < rebels.size(); i++) {
            Rebel rebel = rebels.get(i);
            System.out.println(ProgramConstants.OPTION_COLOR + rebel.getName() + ";" + rebel.getAge() + ";" + rebel.getRace() + ProgramConstants.RESET_COLOR);
        }

        System.out.println();
        System.out.println(ProgramConstants.FINISH_COLOR + "\nFinishing the registration and writing in the file `rebels.txt`!" + ProgramConstants.RESET_COLOR);
    }

    public static void generatedFileSuccessfully() {
        System.out.println(ProgramConstants.FINISH_COLOR + "\nFinished!" + ProgramConstants.RESET_COLOR);
    }

    public static void generatedFileWrongly() {
        System.out.println(ProgramConstants.ERROR_COLOR + "Error writing the records." + ProgramConstants.RESET_COLOR);
    }

}
