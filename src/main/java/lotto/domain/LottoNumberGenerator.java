package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;

public class LottoNumberGenerator {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private LottoNumberGenerator() {
        throw new UnsupportedOperationException();
    }

    public static List<Integer> generate() {
        HashSet<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.LOTTO_SIZE) {
            numbers.add(Randoms.pickNumberInRange(MIN, MAX));
        }
        return List.copyOf(numbers);
    }
}
