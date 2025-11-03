package lotto.domain;

import java.util.HashSet;
import java.util.List;
import lotto.validator.ErrorMessage;

public class Lotto {

    public static final int PRICE = 1000;
    public static final int SIZE = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNoDuplicateNumbers(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage(SIZE));
        }
    }

    private void validateNoDuplicateNumbers(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public WinningResult getWinningResult(WinningNumber winningNumber) {
        int matchCount = getMatchCount(winningNumber.getNumbers());
        boolean matchBonus = numbers.contains(winningNumber.getBonusNumber());

        return new WinningResult(matchCount, matchBonus);
    }

    private int getMatchCount(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
