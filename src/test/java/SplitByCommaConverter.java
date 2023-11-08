import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SplitByCommaConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context)
            throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source);
        }

        String input = (String) source;
        return convert(input);
    }

    private List<Character> convert(String input) {
        String[] parts = input.split(", ");

        return Arrays.stream(parts)
                .filter(part -> part.length() == 1)
                .map(part -> part.charAt(0))
                .collect(toList());
    }

}
