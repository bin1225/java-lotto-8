package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.TotalWinningResult;
import lotto.domain.WinningNumber;
import lotto.util.InputRetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            new Application().run();
        } finally {
            Console.close();
        }
    }

    private void run() {
        //구매 금액 입력
        PurchaseAmount purchaseAmount = InputRetryHandler.retryUntilValid(this::readPurchaseAmount);

        //입력 금액에 따라 로또 발행 및 결과 출력
        Lottos lottos = Lottos.issue(purchaseAmount);
        OutputView.printPurchasedLottos(lottos);

        //당첨 번호 및 보너스 번호 입력
        WinningNumber winningNumber = readWinningNumber();

        //통계 계산 및 출력
        TotalWinningResult totalWinningResult = lottos.getTotalWinningResult(winningNumber);
        OutputView.printTotalWinningResult(totalWinningResult, purchaseAmount);
    }

    private PurchaseAmount readPurchaseAmount() {
        long purchaseAmount = InputView.readPurchaseAmount();
        return new PurchaseAmount(purchaseAmount);
    }

    private WinningNumber readWinningNumber() {
        WinningNumber.Builder builder = new WinningNumber.Builder();

        // 당첨번호 입력
        InputRetryHandler.retryUntilValid(
                InputView::readWinningNumbers,
                builder::setWinningNumbers);

        // 보너스번호 입력
        InputRetryHandler.retryUntilValid(
                InputView::readBonusNumber,
                builder::setBonusNumber);

        return builder.build();
    }
}
