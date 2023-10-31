package io.github.jaewonpro;

class Alphabet {

    private static final char ALPHABET_BEGIN = 'A';
    private static final char ALPHABET_END = 'z';

    private static final char[] VOWEL_LIST = {
            'A', 'E', 'I', 'O', 'U',
            'a', 'e', 'i', 'o', 'u',
    };

    public static boolean isVowel(final char unicode) {
        if (!isAlphabet(unicode)) {
            throw new IllegalArgumentException("The given unicode is not in alphabet: " + unicode);
        }
        for (char vowel : VOWEL_LIST) {
            if (vowel == unicode) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConsonant(final char unicode) {
        return !isVowel(unicode);
    }

    public static boolean isAlphabet(final char unicode) {
        return ALPHABET_BEGIN <= unicode && unicode <= ALPHABET_END;
    }

}
