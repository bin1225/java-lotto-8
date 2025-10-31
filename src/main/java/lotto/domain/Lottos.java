package lotto.domain;

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
            generatedLottos.add(new Lotto(LottoNumberGenerator.generate()));
        }
        return new Lottos(generatedLottos);
    }

    private static long calculateLottoCount(long amount) {
        return amount / Lotto.LOTTO_PRICE;
    }

    public List<Lotto> asList() {
        return lottos;
    }

    public long getTotalWinningAmount(WinningNumber winningNumber) {
        return lottos.stream()
                .mapToLong(lotto -> lotto.getWinningResult(winningNumber).getWinningAmount())
                .sum();
    }
}
