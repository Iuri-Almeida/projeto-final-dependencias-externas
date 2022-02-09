package br.com.ialmeida.application;

import br.com.ialmeida.controller.RegisterController;
import br.com.ialmeida.entities.Rebel;
import br.com.ialmeida.enums.Race;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegisterController controller = new RegisterController();

        UI.printTitle();

        while (controller.rebelsLength() < 10) {
            try {
                UI.clearScreen();

                System.out.printf("Rebel #%d\n", controller.rebelsLength() + 1);

                String name = UI.readString(sc, "Name: ");
                int age = UI.readInt(sc, "Age: ", true);
                int raceOrdinal = UI.readInt(sc, "Choose race:\n", false);

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
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Click ENTER to continue.");
                sc.nextLine();
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
}
