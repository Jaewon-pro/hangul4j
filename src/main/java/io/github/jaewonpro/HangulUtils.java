package io.github.jaewonpro;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class HangulUtils {

    /**
     * Disassemble the Korean Hangul characters within the provided text.
     *
     * @param text - A text to disassemble
     * @return - The list of disassembled characters; other characters will remain intact.
     */
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

    public static CharSequence disassembleToCharSequence(final String text) {
        return text.chars()
                .mapToObj(unicodeValue -> (char) unicodeValue)
                .flatMap(HangulUtils::disassembleHangulCharacterAsStream)
                .map(Object::toString)
                .collect(joining());
    }

    public static String disassembleToString(final String text) {
        return disassembleToCharSequence(text).toString();
    }

    public static String disassembleToString(final char unicodeValue) {
        if (!isCompleteHangulInUnicode(unicodeValue)) {
            return String.valueOf(unicodeValue);
        }

        return HangulComponent.of(unicodeValue).toString();
    }


    /**
     * 문장에 다른 문장이 포함되는지 반환
     *
     * @param text      - 전체 문장
     * @param substring - 찾을 문장
     * @return - 포함하는지 여부
     */
    public static boolean contains(final String text, final String substring) {
        if (text.length() < substring.length()) {
            return false;
        }
        if (text.equals(substring)) {
            return true;
        }

        String disassembledText = disassembleToString(text);
        String disassembledSubstring = disassembleToString(substring);

        return disassembledText.contains(disassembledSubstring);
    }

    /**
     * 자음으로 끝나는 문장인지 반환, 완성형 한글이 아닐 경우, false를 반환
     *
     * @param text - 판단할 문장
     * @return - 자음으로 끝나는지 여부
     */
    public static boolean isEndsWithConsonant(final String text) {
        final char lastUnicode = text.charAt(text.length() - 1);
        return isEndsWithConsonant(lastUnicode);
    }

    /**
     * 자음으로 끝나는 글자인지 반환, 완성형 한글이 아닐 경우, false를 반환
     *
     * @param unicode - 판단할 글자
     * @return - 주어진 {@code unicode}가 자음으로 끝나는지 여부
     */
    public static boolean isEndsWithConsonant(final char unicode) {
        if (!isCompleteHangulInUnicode(unicode)) {
            return false;
        }

        return HangulComponent.of(unicode)
                .isEndsWithConsonant();
    }

    /**
     * <p>
     * 유니코드상의 완성형 한글인지 유무 반환<br>
     * e.g) 한 -> {@code true}, ㅚ -> {@code false}, a -> {@code false}
     * </p>
     *
     * @param unicode - 판단할 글자
     * @return - 주어진 {@code unicode}가 완성형 한글인지 반환
     */
    public static boolean isCompleteHangulInUnicode(final char unicode) {
        return HangulComponent.isCompleteHangulInUnicode(unicode);
    }

    /**
     * 주어진 {@code unicode}가 완성형 한글이면, 초성으로 바꾸어 반환하는 함수
     *
     * @param unicode - 변환할 문자, 올바르지 않는 한글인 경우, 그대로 반환
     * @return - 주어진 {@code unicode}가 초성으로 된 결과
     */
    public static char toOnlyBeginConsonant(final char unicode) {
        if (!isCompleteHangulInUnicode(unicode)) {
            return unicode;
        }

        return HangulComponent.of(unicode).toCharArray()[0];
    }

    public static CharSequence toOnlyBeginConsonant(final String text) {
        return text.chars()
                .mapToObj(unicode -> toOnlyBeginConsonant((char) unicode))
                .map(Object::toString)
                .collect(joining());
    }


    private static Stream<Character> disassembleHangulCharacterAsStream(final char unicodeValue) {
        if (!isCompleteHangulInUnicode(unicodeValue)) {
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
