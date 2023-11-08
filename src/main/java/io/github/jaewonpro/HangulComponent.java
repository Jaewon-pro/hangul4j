package io.github.jaewonpro;

import java.util.Comparator;
import java.util.Objects;

final class HangulComponent implements Comparable<HangulComponent> {

    private static final char HANGUL_UNICODE_BEGIN = '가';
    private static final char HANGUL_UNICODE_END = '힣';

    private static final char EMPTY_CHAR = '-';

    private static final char[] BEGIN_LIST = { // 초성
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    private static final char[] MIDDLE_LIST = { // 중성
            'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ',
            'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'
    };

    private static final char[] END_LIST = { // 종성
            EMPTY_CHAR, 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ',
            'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ',
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    private final char begin; // 초성
    private final char middle; // 중성
    private final char end; // 종성

    public static HangulComponent of(final int codePoint) {
        if (!isCompleteHangulInUnicode(codePoint)) {
            throw new IllegalArgumentException("올바른 한글이 아닌 unicode 입니다! actual: " + codePoint);
        }
        return new HangulComponent(codePoint);
    }

    public static boolean isCompleteHangulInUnicode(final char unicode) {
        return HANGUL_UNICODE_BEGIN <= unicode && unicode <= HANGUL_UNICODE_END;
    }

    public static boolean isCompleteHangulInUnicode(final int codePoint) {
        return HANGUL_UNICODE_BEGIN <= codePoint && codePoint <= HANGUL_UNICODE_END;
    }


    public char[] toCharArray() {
        if (isEmptyChar(end)) {
            return new char[]{begin, middle};
        } else {
            return new char[]{begin, middle, end};
        }
    }

    public CharSequence toCharSequence() {
        StringBuilder stringBuilder = new StringBuilder(3);
        stringBuilder.append(toCharArray());
        return stringBuilder;
    }


    @Override
    public String toString() {
        return toCharSequence().toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HangulComponent)) return false;
        final HangulComponent that = (HangulComponent) o;
        return begin == that.begin &&
                middle == that.middle &&
                end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, middle, end);
    }

    @Override
    public int compareTo(final HangulComponent o) {
        return byBegin.thenComparing(byMiddle).thenComparing(byEnd).compare(this, o);
    }

    public static final Comparator<HangulComponent> byBegin = Comparator
            .comparing(h -> h.begin);

    public static final Comparator<HangulComponent> byMiddle = Comparator
            .comparing(h -> h.middle);

    public static final Comparator<HangulComponent> byEnd = Comparator
            .comparing(h -> h.end);


    public boolean isEndsWithConsonant() {
        return end != EMPTY_CHAR;
    }

    private HangulComponent(final int codePoint) {
        final int difference = codePoint - HANGUL_UNICODE_BEGIN;

        this.begin = BEGIN_LIST[getBeginIndex(difference)];
        this.middle = MIDDLE_LIST[getMiddleIndex(difference)];
        this.end = END_LIST[getEndIndex(difference)];
    }

    private boolean isEmptyChar(final char unicodeValue) {
        return unicodeValue == EMPTY_CHAR;
    }

    private static int getBeginIndex(final int value) {
        return value / END_LIST.length / MIDDLE_LIST.length;
    }

    private static int getMiddleIndex(final int value) {
        return value / END_LIST.length % MIDDLE_LIST.length;
    }

    private static int getEndIndex(final int value) {
        return value % END_LIST.length;
    }


}
