package lotto.domain;

import static lotto.validator.ErrorMessage.DUPLICATE_BONUS_NUMBER;
import static lotto.validator.ErrorMessage.DUPLICATE_WINNING_NUMBER;
import static lotto.validator.ErrorMessage.INVALID_WINNING_NUMBER_COUNT;
import static lotto.validator.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.HashSet;
import java.util.List;
import lotto.validator.ErrorMessage;

public class WinningNumber {

    private final List<Integer> numbers;
    private final int bonusNumber;

    private WinningNumber(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

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

    public static void validateWinningNumbers(List<Integer> numbers) {
        validateSize(numbers);
        numbers.forEach(WinningNumber::validateNumberRange);
        validateNoDuplicate(numbers);
    }

    private static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        validateNumberRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != Lotto.SIZE) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_COUNT.getMessage(Lotto.SIZE));
        }
    }

    private static void validateNoDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER.getMessage());
        }
    }

    private static void validateNumberRange(int number) {
        if (number < Lotto.MIN_NUMBER || number > Lotto.MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
