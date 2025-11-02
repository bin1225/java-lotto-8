package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> generatedLottos) {
        this.lottos = List.copyOf(generatedLottos);
    }

    public static Lottos generateLottos(long amount) {
        List<Lotto> generatedLottos = new ArrayList<>();
        long count = calculateLottoCount(amount);
        while (count-- > 0) {
            generatedLottos.add(
                    new Lotto(Randoms.pickUniqueNumbersInRange(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER, Lotto.SIZE)));
        }
        return new Lottos(generatedLottos);
    }

    private static long calculateLottoCount(long amount) {
        return amount / Lotto.PRICE;
    }

    public List<Lotto> asList() {
        return lottos;
    }

    public TotalWinningResult getTotalWinningResult(WinningNumber winningNumber) {
        List<WinningResult> winningResults = lottos.stream().map(lotto -> lotto.getWinningResult(winningNumber))
                .toList();
        return TotalWinningResult.from(winningResults);
    }
}
