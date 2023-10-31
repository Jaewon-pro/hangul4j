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
    public static List<Character> disassembleToList(final String text) {
        return text.chars()
                .mapToObj(unicodeValue -> (char) unicodeValue)
                .flatMap(HangulUtils::disassembleHangulCharacterToStream)
                .collect(toList());
    }

    public static List<Character> disassembleToList(final char unicodeValue) {
        return disassembleHangulCharacterToStream(unicodeValue)
                .collect(toList());
    }

    public static CharSequence disassembleToCharSequence(final String text) {
        return text.chars()
                .mapToObj(unicodeValue -> (char) unicodeValue)
                .flatMap(HangulUtils::disassembleHangulCharacterToStream)
                .map(Object::toString)
                .collect(joining());
    }

    /**
     * <p>
     * 한글들은 초성, 중성, 종성으로 분해한 결과를 반환하는 함수
     * </p>
     * @param text - 분해할 문자열
     * @return
     */
    public static String disassemble(final String text) {
        return disassembleToCharSequence(text).toString();
    }

    public static String disassemble(final char unicodeValue) {
        if (!isCompleteHangulInUnicode(unicodeValue)) {
            return String.valueOf(unicodeValue);
        }

        return HangulComponent.of(unicodeValue).toString();
    }


    /**
     * 문자열에 다른 문자열이 포함되는지 반환
     *
     * @param text      - 전체 문자열
     * @param substring - 찾을 문자열
     * @return - 포함하는지 여부
     */
    public static boolean contains(final String text, final String substring) {
        if (text.length() < substring.length()) {
            return false;
        }
        if (text.equals(substring)) {
            return true;
        }

        String disassembledText = disassemble(text);
        String disassembledSubstring = disassemble(substring);

        return disassembledText.contains(disassembledSubstring);
    }

    /**
     * 자음으로 끝나는 문자열인지 반환, 완성형 한글이 아닐 경우, false를 반환
     *
     * @param text - 판단할 문자열
     * @return - 자음으로 끝나는지 여부
     */
    public static boolean isEndsWithConsonant(final String text) {
        final char lastUnicode = text.charAt(text.length() - 1);
        return isEndsWithConsonant(lastUnicode);
    }

    /**
     * 자음으로 끝나는 글자인지 반환, 완성형 한글이 아닐 경우, false를 반환
     *
     * @param unicode - 판단할 문자
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
     * 한글과 영문 알파벳과 함께 검사해서 자음으로 끝나는 글자인지 반환<br>
     * a, e, i, o, u만 모음으로 판단<br>
     * </p>
     *
     * @param unicode - 판단할 문자
     * @return - 주어진 {@code unicode}가 자음으로 끝나는지 여부
     */
    public static boolean isEndsWithConsonantWithLatin(final char unicode) {
        if (Alphabet.isAlphabet(unicode) && Alphabet.isConsonant(unicode)) {
            return true;
        }
        return isEndsWithConsonant(unicode);
    }

    /**
     * <p>
     * 한글과 영문 알파벳과 함께 검사해서 자음으로 끝나는 글자인지 반환<br>
     * a, e, i, o, u만 모음으로 판단<br>
     * </p>
     *
     * @param text - 판단할 문자열
     * @return - 주어진 {@code text}가 자음으로 끝나는지 여부
     */
    public static boolean isEndsWithConsonantWithLatin(final String text) {
        final char lastUnicode = text.charAt(text.length() - 1);
        return isEndsWithConsonantWithLatin(lastUnicode);
    }

    /**
     * <p>
     * 유니코드상의 완성형 한글인지 유무 반환<br>
     * e.g) 한 -> {@code true}, ㅚ -> {@code false}, a -> {@code false}
     * </p>
     *
     * @param unicode - 판단할 문자
     * @return - 주어진 {@code unicode}가 완성형 한글인지 반환
     */
    public static boolean isCompleteHangulInUnicode(final char unicode) {
        return HangulComponent.isCompleteHangulInUnicode(unicode);
    }

    /**
     * <p>
     * 주어진 {@code unicode}가 완성형 한글이면, 초성으로 바꾸어 반환하는 함수<br>
     * 완성형 한글이 아니라 초성으로 만들수 없는 경우, 그대로 반환
     * </p>
     *
     * @param unicode - 변환할 문자
     * @return - 주어진 {@code unicode}가 초성으로 된 결과
     */
    public static char toOnlyBeginConsonant(final char unicode) {
        if (!isCompleteHangulInUnicode(unicode)) {
            return unicode;
        }

        return HangulComponent.of(unicode).toCharArray()[0];
    }

    /**
     * <p>
     * 주어진 {@code unicode}가 완성형 한글이면, 초성으로 바꾸어 반환하는 함수<br>
     * 완성형 한글이 아니라 초성으로 만들수 없는 경우, 그대로 반환
     * </p>
     *
     * @param text - 변환할 문자열
     * @return - 주어진 {@code text}가 초성으로 변환된 문자열
     */
    public static String toOnlyBeginConsonant(final String text) {
        return text.chars()
                .mapToObj(unicode -> toOnlyBeginConsonant((char) unicode))
                .map(Object::toString)
                .collect(joining());
    }


    private static Stream<Character> disassembleHangulCharacterToStream(final char unicodeValue) {
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
