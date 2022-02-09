package br.com.ialmeida.sort;

import br.com.ialmeida.entities.Rebel;

import java.util.HashMap;

public class BubbleSort {

    public static void sortByName(HashMap<Integer, Rebel> rebels) {
        for (int i = 0; i < rebels.size() - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < rebels.size() - 1 - i; j++) {
                if(rebels.get(j).getName().compareTo(rebels.get(j + 1).getName()) > 0) {
                    Rebel aux = rebels.get(j);
                    rebels.put(j, rebels.get(j + 1));
                    rebels.put(j + 1, aux);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void sortByAge(HashMap<Integer, Rebel> rebels) {
        for (int i = 0; i < rebels.size() - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < rebels.size() - 1 - i; j++) {
                if(rebels.get(j).getAge() > rebels.get(j + 1).getAge()) {
                    Rebel aux = rebels.get(j);
                    rebels.put(j, rebels.get(j + 1));
                    rebels.put(j + 1, aux);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void sortByRace(HashMap<Integer, Rebel> rebels) {
        for (int i = 0; i < rebels.size() - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < rebels.size() - 1 - i; j++) {
                if(rebels.get(j).getRace().name().compareTo(rebels.get(j + 1).getRace().name()) > 0) {
                    Rebel aux = rebels.get(j);
                    rebels.put(j, rebels.get(j + 1));
                    rebels.put(j + 1, aux);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }
}
