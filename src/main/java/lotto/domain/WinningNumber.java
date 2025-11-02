package lotto.domain;

import static lotto.validator.ErrorMessage.DUPLICATE_BONUS_NUMBER;
import static lotto.validator.ErrorMessage.DUPLICATE_WINNING_NUMBER;
import static lotto.validator.ErrorMessage.INVALID_NUMBER_COUNT;
import static lotto.validator.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public record WinningNumber(List<Integer> numbers, int bonusNumber) {
    public WinningNumber(List<Integer> numbers, int bonusNumber) {
        validateCount(numbers);
        validateNumberInRange(numbers, bonusNumber);
        validateDuplicate(numbers, bonusNumber);
        this.numbers = List.copyOf(numbers);
        this.bonusNumber = bonusNumber;
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != Lotto.SIZE) {
            throw new IllegalArgumentException(
                    INVALID_NUMBER_COUNT.getMessage(Lotto.SIZE));
        }
    }

    private static void validateNumberInRange(List<Integer> numbers, int bonusNumber) {
        Stream<Integer> all = Stream.concat(numbers.stream(), Stream.of(bonusNumber));
        if (all.anyMatch(n -> n < Lotto.MIN_NUMBER || n > Lotto.MAX_NUMBER)) {
            throw new IllegalArgumentException(
                    INVALID_NUMBER_RANGE.getMessage(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
            );
        }
    }

    private static void validateDuplicate(List<Integer> numbers, int bonusNumber) {
        // 당첨 번호 내 중복 확인
        HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER.getMessage());
        }

        // 보너스 번호 중복 확인
        if (!uniqueNumbers.add(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
