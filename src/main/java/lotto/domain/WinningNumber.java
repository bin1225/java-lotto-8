package lotto.domain;

import static lotto.validator.ErrorMessage.DUPLICATE_BONUS_NUMBER;
import static lotto.validator.ErrorMessage.DUPLICATE_WINNING_NUMBER;
import static lotto.validator.ErrorMessage.INVALID_NUMBER_COUNT;
import static lotto.validator.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.HashSet;
import java.util.List;
import lotto.validator.ErrorMessage;

public record WinningNumber(List<Integer> numbers, int bonusNumber) {

    public static class Builder {
        private List<Integer> winningNumbers;
        private Integer bonusNumber;

        public Builder setWinningNumbers(List<Integer> numbers) {
            validateWinningNumbers(numbers);
            this.winningNumbers = numbers;
            return this;
        }

        public Builder setBonusNumber(int bonusNumber) {
            if (winningNumbers == null) {
                throw new IllegalStateException(ErrorMessage.WINNING_NUMBER_NOT_SET.getMessage());
            }
            validateBonusNumber(bonusNumber, winningNumbers);
            this.bonusNumber = bonusNumber;
            return this;
        }

        public WinningNumber build() {
            if (winningNumbers == null || bonusNumber == null) {
                throw new IllegalStateException(ErrorMessage.INCOMPLETE_WINNING_NUMBER.getMessage());
            }
            return new WinningNumber(winningNumbers, bonusNumber);
        }
    }

    private static void validateWinningNumbers(List<Integer> numbers) {
        if (numbers.size() != Lotto.SIZE) {
            throw new IllegalArgumentException(
                    INVALID_NUMBER_COUNT.getMessage(Lotto.SIZE)
            );
        }

        if (numbers.stream().anyMatch(n -> n < Lotto.MIN_NUMBER || n > Lotto.MAX_NUMBER)) {
            throw new IllegalArgumentException(
                    INVALID_NUMBER_RANGE.getMessage(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
            );
        }

        HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER.getMessage());
        }
    }

    private static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < Lotto.MIN_NUMBER || bonusNumber > Lotto.MAX_NUMBER) {
            throw new IllegalArgumentException(
                    INVALID_NUMBER_RANGE.getMessage(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
            );
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
