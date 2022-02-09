package br.com.ialmeida.entities;

import br.com.ialmeida.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rebel {
    private final String name;
    private final int age;
    private final Race race;
}
