package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {

    private static final String PURCHASED_COUNT_MESSAGE = "\n%d개를 구매했습니다.\n";


    private OutputView() {
        throw new UnsupportedOperationException();
    }

    public static void printPurchasedLottos(Lottos lottos) {
        List<Lotto> lottoList = lottos.asList();
        System.out.printf(PURCHASED_COUNT_MESSAGE, lottoList.size());

        lottoList.forEach(System.out::println);
        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
