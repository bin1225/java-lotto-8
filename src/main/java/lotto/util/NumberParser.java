package lotto.util;


import static lotto.validator.ErrorMessage.INVALID_NUMBER;
import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import java.util.Arrays;
import java.util.List;
import lotto.validator.InputValidator;

public class NumberParser {

    private NumberParser() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static List<Integer> parseNumbers(String input, String delimiter) {
        InputValidator.validateEmpty(input);
        return parseByDelimiter(input, delimiter);
    }

    public static int parseSingleNumber(String input) {
        InputValidator.validateEmpty(input);
        InputValidator.validateNumeric(input);
        return Integer.parseInt(input);
    }

    private static List<Integer> parseByDelimiter(String input, String delimiter) {
        InputValidator.validateEmpty(input);
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    InputValidator.validateNumeric(s);
                    return Integer.parseInt(s);
                })
                .toList();
    }
}
