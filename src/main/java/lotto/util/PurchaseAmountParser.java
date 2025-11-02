package lotto.util;

import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import lotto.domain.Lotto;
import lotto.validator.ErrorMessage;
import lotto.validator.InputValidator;

public class PurchaseAmountParser {

    private PurchaseAmountParser() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static long parse(String input) {
        InputValidator.validateEmpty(input);
        InputValidator.validateNumeric(input);
        InputValidator.validateWithinLongRange(input);
        long amount = Long.parseLong(input);
        validatePositive(amount);
        validateUnit(amount);
        return amount;
    }

    private static void validateUnit(long amount) {
        if (amount % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT.getMessage(Lotto.PRICE));
        }
    }

    private static void validatePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER.getMessage());
        }
    }
}

