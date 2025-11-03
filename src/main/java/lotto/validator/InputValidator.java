package lotto.validator;

import static lotto.validator.ErrorMessage.EMPTY_INPUT;

public class InputValidator {

    private InputValidator() {
        throw new UnsupportedOperationException(ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
        }
    }

    public static void validateWithinIntegerRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    ErrorMessage.OUT_OF_RANGE.getMessage(Integer.MIN_VALUE, Integer.MAX_VALUE));
        }
    }

    public static void validateWithinLongRange(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE.getMessage(Long.MIN_VALUE, Long.MAX_VALUE));
        }
    }
}
