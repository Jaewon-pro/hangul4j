import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.ArrayList;
import java.util.List;

public class SplitByCommaConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context)
            throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source);
        }
        String[] parts = ((String) source).split(", ");

        final List<Character> a = new ArrayList<>();
        for (String part : parts) {
            if (part.length() > 1) {
                throw new IllegalArgumentException("Failed to convert: " + part);
            }

            a.add(part.charAt(0));
        }

        return a;
    }
}
