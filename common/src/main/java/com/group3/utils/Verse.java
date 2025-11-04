package com.group3.utils;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Verse {

    private final List<String> verses = List.of(
            "La lluvia golpea tras de mi...",
            "I'll see you on the dark side of the moon"
    );

    public String getRandomVerse(){
        Random random = new Random();

        return verses.get(random.nextInt(verses.size()));
    }
}
