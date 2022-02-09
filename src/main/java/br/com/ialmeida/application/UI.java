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

    public static String readString(Scanner sc, String txt) {
        System.out.print(txt);
        return sc.nextLine();
    }

    public static int readInt(Scanner sc, String txt, boolean isAge) {
        if (isAge) {
            System.out.print(txt);
        } else {
            System.out.println(txt);
            for (Race race : Race.values()) {
                System.out.printf("%d - %s\n", race.ordinal(), race.name());
            }

            System.out.print("\nChoose: ");
        }

        try {
            String n = sc.nextLine();
            return Integer.parseInt(n);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading integer inputs.");
        }
    }
}
