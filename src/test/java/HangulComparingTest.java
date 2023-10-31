import io.github.jaewonpro.HangulUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HangulComparingTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/compare/containing.csv", numLinesToSkip = 1)
    void givenText_whenCheckContaining_returnResult(
            final String input,
            final String substring,
            final boolean expected
    ) {
        final boolean actual = HangulUtils.contains(input, substring);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/compare/end-consonant.csv", numLinesToSkip = 1)
    void givenUnicode_whenCheckEnd_returnResult(
            final char input,
            final boolean expected
    ) {
        final boolean actual = HangulUtils.isEndsWithConsonant(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/compare/text-end-consonant.csv", numLinesToSkip = 1)
    void givenText_whenCheckEnd_returnResult(
            final String input,
            final boolean expected
    ) {
        final boolean actual = HangulUtils.isEndsWithConsonant(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/compare/text-end-consonant-with-latin.csv", numLinesToSkip = 1)
    void givenText_whenCheckEndWithLatin_returnResult(
            final String input,
            final boolean expected
    ) {
        final boolean actual = HangulUtils.isEndsWithConsonantWithLatin(input);
        assertThat(actual).isEqualTo(expected);
    }

}
