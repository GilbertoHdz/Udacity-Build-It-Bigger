package com.manitos.dev.datalib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokesProvider {

    private final List<String> _jokes = new ArrayList<String>(Arrays.asList(
            "Descartes walked into a bar and ordered a beer. “Want another?” asked the bartender. “I think not”, Descartes replied … then he disappeared.",
            "Q: What do you call a boomerang that doesn't work? A: A stick.",
            "Q:  What do you call a guy who’s had too much to drink? A:  A cab.",
            "Q. What does a cat have that no other animal has? A. Kittens.",
            "Q: Wanna hear a bad cat joke? A: Just kitten!",
            "Q: What did the grandma cat say to her grandson when she saw him slouching? A: You need to pay more attention to my pawsture.",
            "Q. How do dog catchers get paid? A. By the pound!",
            "Q: What do chemists' dogs do with their bones? A: They barium!",
            "Q. Why aren't dogs good dancers? A. Because they have two left feet!"
    ));

    public String getJoke() {
        Random random = new Random();

        String randomJoke = _jokes.get(random.nextInt(_jokes.size()));

        return randomJoke;
    }
}
