package br.com.ialmeida.application;

import br.com.ialmeida.controller.RegisterController;
import br.com.ialmeida.entities.Rebel;
import br.com.ialmeida.enums.Race;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegisterController controller = new RegisterController();

        System.out.println("Rebels Registration!\n");

        while (controller.rebelsLength() < 10) {
            clearScreen();

            System.out.printf("Rebel #%d\n", controller.rebelsLength() + 1);

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();

            System.out.println("Choose race:\n");
            for (Race race : Race.values()) {
                System.out.printf("%d - %s\n", race.ordinal(), race.name());
            }

            System.out.print("\nChoose: ");
            int raceOrdinal = sc.nextInt();

            Rebel rebel = new Rebel(name, age, Race.values()[raceOrdinal]);

            System.out.print("\nWanna join us? [y/n] ");
            char answer = sc.next().toLowerCase().charAt(0);
            sc.nextLine();

            if (answer == 'y') {
                if (controller.requestAccess(rebel)) {
                    controller.registerRebel(rebel);
                } else {
                    System.out.println("\nACCESS DENIED!\n");
                }
            }

        }

        System.out.println("\nFinishing the registration and writing in the file `rebels.txt`!");

        try {
            controller.generateTxtFile();
            System.out.println("\nFinished!");
        } catch (IOException e) {
            System.out.println("Error writing the records.");
            e.printStackTrace();
        }

        sc.close();
    }

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
