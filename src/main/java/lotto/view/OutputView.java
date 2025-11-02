package lotto.view;

import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.TotalWinningResult;

public class OutputView {

    private static final String PURCHASED_COUNT_MESSAGE = "\n%d개를 구매했습니다.\n";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String DIVIDER = "---";
    private static final String RESULT_FORMAT = "%d개 일치%s (%s원) - %d개\n";
    private static final String BONUS_TEXT = ", 보너스 볼 일치";
    private static final String RATE_OF_RETURN_MESSAGE = "총 수익률은 %.1f%%입니다.\n";

    private OutputView() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static void printPurchasedLottos(Lottos lottos) {
        List<Lotto> lottoList = lottos.asList();
        System.out.printf(PURCHASED_COUNT_MESSAGE, lottoList.size());

        lottoList.forEach(System.out::println);
        printNewLine();
    }

    public static void printTotalWinningResult(TotalWinningResult totalWinningResult, long purchaseAmount) {
        System.out.println(STATISTICS_HEADER);
        System.out.println(DIVIDER);

        printWinningStatistics(totalWinningResult);
        printRateOfReturn(totalWinningResult, purchaseAmount);
    }

    private static void printWinningStatistics(TotalWinningResult totalWinningResult) {
        Map<Rank, Integer> stats = totalWinningResult.getWinningStatistics();

        for (Rank rank : Rank.values()) {
            if (rank == Rank.MISS) {
                continue; // 꽝은 통계에 제외
            }
            String bonusInfo = rank.isMatchBonus() ? BONUS_TEXT : "";
            String formattedReward = String.format("%,d", rank.getReward());
            System.out.printf(RESULT_FORMAT,
                    rank.getMatchCount(),
                    bonusInfo,
                    formattedReward,
                    stats.getOrDefault(rank, 0));
        }
    }

    private static void printRateOfReturn(TotalWinningResult totalWinningResult, long purchaseAmount) {
        double rate = totalWinningResult.getRateOfReturn(purchaseAmount);
        System.out.printf(RATE_OF_RETURN_MESSAGE, rate);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
