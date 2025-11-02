package lotto.validator;

import lotto.domain.Lotto;

public class PurchaseAmountValidator {

    private PurchaseAmountValidator() {
        throw new UnsupportedOperationException();
    }

    public static long validate(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
        validateWithinLongRange(input);
        long amount = Long.parseLong(input);
        validatePositive(amount);
        validateUnit(amount);
        return amount;
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_AMOUNT.getMessage());
        }
    }

    private static void validateNumeric(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
        }
    }

    /**
     * 타입 표현 범위 초과 검증
     */
    private static void validateWithinLongRange(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE.getMessage(Long.MIN_VALUE, Long.MAX_VALUE));
        }
    }

    private static void validatePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER.getMessage());
        }
    }

    private static void validateUnit(long amount) {
        if (amount % Lotto.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT.getMessage(Lotto.LOTTO_PRICE));
        }
    }
}
