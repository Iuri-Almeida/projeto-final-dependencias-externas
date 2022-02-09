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

        UI.printTitle(sc);

        while (controller.rebelsLength() < ProgramConstants.TOTAL_REBELS) {
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
                System.out.println(ProgramConstants.ERROR_COLOR + e.getMessage() + "\n");
                System.out.println("Click ENTER to continue." + ProgramConstants.RESET_COLOR);
                sc.nextLine();
            }

        }

        UI.clearScreen();
        UI.orderTheRebels(sc, controller.getRebels());

        try {
            controller.generateTxtFile();
            UI.generatedFileSuccessfully();
        } catch (IOException e) {
            UI.generatedFileWrongly();
            e.printStackTrace();
        }

        sc.close();
    }
}
