package lotto.domain;

import lotto.validator.ErrorMessage;

public record PurchaseAmount(long value) {

    public PurchaseAmount(long value) {
        validatePositive(value);
        validateUnit(value);
        this.value = value;
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
