package lotto.domain;

import lotto.validator.ErrorMessage;

public record PurchaseAmount(long value) {

    public PurchaseAmount {
        validatePositive(value);
        validateDividedUp(value);
    }

    private static void validateDividedUp(long amount) {
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
