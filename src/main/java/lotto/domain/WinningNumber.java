package lotto.domain;

import java.util.List;

public record WinningNumber(List<Integer> numbers, int bonusNumber) {
    public WinningNumber(List<Integer> numbers, int bonusNumber) {
        this.numbers = List.copyOf(numbers);
        this.bonusNumber = bonusNumber;
    }
}
