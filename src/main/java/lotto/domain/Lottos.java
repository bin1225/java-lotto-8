package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> generatedLottos) {
        this.lottos = List.copyOf(generatedLottos);
    }

    public static Lottos generateLottos(int count) {
        List<Lotto> generatedLottos = new ArrayList<>();
        while (count-- > 0) {
            generatedLottos.add(new Lotto(LottoGenerator.generate()));
        }
        return new Lottos(generatedLottos);
    }

    public List<Lotto> getValue() {
        return lottos;
    }
}
