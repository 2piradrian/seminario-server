package com.group3.utils;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Verse {

    private final List<String> verses = List.of(
            "La lluvia golpea tras de mi...",
            "I'll see you on the dark side of the moon",
            "If I'm dreaming, baby, please don't wake me up",
            "El día que me quieras endulzará sus cuerdas el pájaro cantor",
            "¿Qué importa perderme mil veces la vida?",
            "Tu amor es una estrella con cuerdas de guitarra",
            "But I would die for you in secret",
            "Mi universo paralelo",
            "Anita prestame el grabador",
            "El final es en donde partí",
            "Cuantas veces en el cielo, cuantas en la oscuridad",
            "Eres tu o soy yo, encontremos la forma de vernos ahora"
    );

    public String getRandomVerse(){
        Random random = new Random();

        return verses.get(random.nextInt(verses.size()));
    }
}
