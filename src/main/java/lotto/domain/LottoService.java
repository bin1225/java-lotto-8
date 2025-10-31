package lotto.domain;

public class LottoService {

    public Lottos issueLottos(long amount) {
        long count = calculateLottoCount(amount);
        return Lottos.generateLottos(count);
    }

    private long calculateLottoCount(long amount) {
        return amount / Lotto.LOTTO_PRICE;
    }
}
