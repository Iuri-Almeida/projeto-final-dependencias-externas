package br.com.ialmeida.application;

import br.com.ialmeida.enums.Race;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printTitle() {
        System.out.println("Rebels Registration!\n");
    }

    public static void printRebelTitle(int n) {
        System.out.printf("Rebel #%d\n", n + 1);
    }

    public static String readName(Scanner sc, String txt) {
        System.out.print(txt);
        return sc.nextLine().trim();
    }

    public static int readAge(Scanner sc, String txt) {
        System.out.print(txt);
        try {
            String n = sc.nextLine().trim();
            return Integer.parseInt(n);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading integer inputs.");
        }
    }

    public static Race readRace(Scanner sc, String txt) {
        System.out.println(txt);
        for (Race race : Race.values()) {
            System.out.printf("%d - %s\n", race.ordinal(), race.name());
        }

        System.out.print("\nChoose: ");

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
            System.out.print(msg + "\nWanna join us? [y/n] ");
            answer = sc.next().trim().toLowerCase().charAt(0);
            sc.nextLine();
            msg = "\nSorry, we will only accept `y` or `n`. ";
        } while (answer != 'y' && answer != 'n');

        return answer == 'y';
    }

    public static void printEndOfRegistration() {
        System.out.println("\nFinishing the registration and writing in the file `rebels.txt`!");
    }
}
