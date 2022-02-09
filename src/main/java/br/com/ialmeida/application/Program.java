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
                UI.printRebelTitle(controller.rebelsLength());

                String name = UI.readName(sc, "Name: ");
                int age = UI.readAge(sc, "Age: ");
                Race race = UI.readRace(sc, "Choose race:\n");

                Rebel rebel = new Rebel(name, age, race);

                if (UI.wannaJoinUs(sc)) {
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

        UI.printEndOfRegistration();

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
