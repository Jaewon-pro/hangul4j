import io.github.jaewonpro.HangulUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HangulUtilsDisassembleTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/disassemble/text-to-string.csv", numLinesToSkip = 1)
    void givenText_whenDissemble_returnDisassembleString(String input, String expected) {
        final String actual = HangulUtils.disassemble(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/disassemble/text-to-list.csv", numLinesToSkip = 1)
    void givenText_whenDissemble_returnDisassembledList(
            String input,
            @ConvertWith(SplitByCommaConverter.class) List<Character> expected
    ) {
        final List<Character> actual = HangulUtils.disassembleToList(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/disassemble/character-to-string.csv", numLinesToSkip = 1)
    void givenChar_whenDissemble_returnDisassembledString(char input, String expected) {
        final String actual = HangulUtils.disassemble(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/disassemble/character-to-list.csv", numLinesToSkip = 1)
    void givenChar_whenDissemble_returnDisassembledList(
            char input,
            @ConvertWith(SplitByCommaConverter.class) List<Character> expected
    ) {
        final List<Character> actual = HangulUtils.disassembleToList(input);
        assertThat(actual).isEqualTo(expected);
    }

}
