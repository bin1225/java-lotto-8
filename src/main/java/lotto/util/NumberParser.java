package lotto.util;


import static lotto.validator.ErrorMessage.EMPTY_INPUT;
import static lotto.validator.ErrorMessage.INVALID_NUMBER;
import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import java.util.Arrays;
import java.util.List;

public class NumberParser {

    private NumberParser() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static List<Integer> parseNumbers(String input, String delimiter) {
        validateEmpty(input);
        return parseByDelimiter(input, delimiter);
    }

    public static int parseSingleNumber(String input) {
        validateEmpty(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }

    private static List<Integer> parseByDelimiter(String input, String delimiter) {
        validateEmpty(input);
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
                    }
                })
                .toList();
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }
}
