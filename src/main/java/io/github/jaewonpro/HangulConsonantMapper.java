package io.github.jaewonpro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

final class HangulConsonantMapper {
    private static final Map<Character, int[]> HANGUL_CONSONANT = new HashMap<>();

    static {
        put('ㄲ', 'ㄱ', 'ㄱ');
        put('ㄸ', 'ㄷ', 'ㄷ');
        put('ㅃ', 'ㅂ', 'ㅂ');
        put('ㅆ', 'ㅅ', 'ㅅ');
        put('ㅉ', 'ㅈ', 'ㅈ');

        put('ㄳ', 'ㄱ', 'ㅈ');
        put('ㄵ', 'ㄴ', 'ㅈ');
        put('ㄶ', 'ㄴ', 'ㅎ');
        put('ㄺ', 'ㄹ', 'ㄱ');
        put('ㄻ', 'ㄹ', 'ㅁ');
        put('ㄼ', 'ㄹ', 'ㅂ');
        put('ㄽ', 'ㄹ', 'ㅅ');
        put('ㄾ', 'ㄹ', 'ㅌ');
        put('ㄿ', 'ㄹ', 'ㅍ');
        put('ㅄ', 'ㅂ', 'ㅅ');
    }

    public static IntStream convert(final int codePoint) {
        Optional<int[]> optionalArray = Optional.ofNullable(HANGUL_CONSONANT.get((char) codePoint));
        return optionalArray.map(Arrays::stream)
                .orElse(IntStream.of(codePoint));
    }

    private static void put(final char codePoint, final int first, final int second) {
        HANGUL_CONSONANT.put(codePoint, new int[]{first, second});
    }

    private HangulConsonantMapper() {
    }

}
