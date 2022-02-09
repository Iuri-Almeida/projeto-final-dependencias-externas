package br.com.ialmeida.controller;

import br.com.ialmeida.entities.Rebel;
import br.com.ialmeida.enums.Race;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@NoArgsConstructor
public class RegisterController {
    private final ArrayList<Rebel> rebels = new ArrayList<>();

    public void registerRebel(Rebel rebel) {
        rebels.add(rebel);
    }

    public int rebelsLength() {
        return rebels.size();
    }

    public void generateTxtFile() throws IOException {
        @Cleanup PrintWriter writer = new PrintWriter("rebels.txt", StandardCharsets.UTF_8);

        for (Rebel rebel : rebels) {
            writer.println(rebel.getName() + ";" + rebel.getAge() + ";" + rebel.getRace());
        }
    }

    public boolean requestAccess(Rebel rebel) {
        return (rebel.getAge() > 20 && rebel.getAge() < 50 && rebel.getRace() == Race.HUMAN) ||
                (rebel.getAge() > 30 && rebel.getAge() < 70 && rebel.getRace() == Race.GREE) ||
                (rebel.getAge() > 0 && rebel.getAge() < 20 && rebel.getRace() == Race.RAKATA);
    }
}
