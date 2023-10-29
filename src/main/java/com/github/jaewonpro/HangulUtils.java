package com.github.jaewonpro;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.jaewonpro.HangulComponent.isValidHangulInUnicode;
import static java.util.stream.Collectors.toList;

public final class HangulUtils {

    public static List<Character> disassemble(final String text) {
        return text.chars()
                .mapToObj(unicodeValue -> (char) unicodeValue)
                .flatMap(HangulUtils::disassembleHangulCharacterAsStream)
                .collect(toList());
    }

    public static List<Character> disassemble(final char unicodeValue) {
        return disassembleHangulCharacterAsStream(unicodeValue)
                .collect(toList());
    }

    public static CharSequence disassembleAsCharSequence(final String text) {
        return text.chars()
                .mapToObj(unicodeValue -> (char) unicodeValue)
                .flatMap(HangulUtils::disassembleHangulCharacterAsStream)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static String disassembleAsString(final String text) {
        return disassembleAsCharSequence(text).toString();
    }

    public static String disassembleAsString(final char unicodeValue) {
        if (!isValidHangulInUnicode(unicodeValue)) {
            return String.valueOf(unicodeValue);
        }

        return HangulComponent.of(unicodeValue).toString();
    }


    private static Stream<Character> disassembleHangulCharacterAsStream(final char unicodeValue) {
        if (!isValidHangulInUnicode(unicodeValue)) {
            return Stream.of(unicodeValue);
        }

        return HangulComponent.of(unicodeValue)
                .toCharSequence()
                .chars()
                .mapToObj(integer -> (char) integer);
    }

    private HangulUtils() {
    }

}
