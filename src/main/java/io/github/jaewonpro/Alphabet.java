package io.github.jaewonpro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

final class Alphabet {

    private static final char ALPHABET_BEGIN = 'A';
    private static final char ALPHABET_END = 'z';

    private static final List<Character> VOWEL_LIST = Stream.of(
                    'A', 'E', 'I', 'O', 'U',
                    'a', 'e', 'i', 'o', 'u')
            .collect(toCollection(ArrayList::new));

    public static boolean isVowel(final char unicode) {
        return VOWEL_LIST.contains(unicode);
    }

    public static boolean isConsonant(final char unicode) {
        return !isVowel(unicode);
    }

    public static boolean isAlphabet(final char unicode) {
        return ALPHABET_BEGIN <= unicode && unicode <= ALPHABET_END;
    }

    private Alphabet() {
    }

}
