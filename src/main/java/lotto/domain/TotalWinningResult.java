package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalWinningResult {

    private final Map<Rank, Integer> winningStatistics;
    private long totalWinningAmount;

    private TotalWinningResult(Map<Rank, Integer> winningStatistics, long totalWinningAmount) {
        this.winningStatistics = winningStatistics;
        this.totalWinningAmount = totalWinningAmount;
    }

    public static TotalWinningResult from(List<WinningResult> winningResults) {
        Map<Rank, Integer> statistics = new HashMap<>();
        long totalWinningAmount = 0;

        for (WinningResult winningResult : winningResults) {
            Rank rank = winningResult.getRank();
            statistics.merge(rank, 1, Integer::sum);
            totalWinningAmount += rank.getReward();
        }

        return new TotalWinningResult(statistics, totalWinningAmount);
    }

    /**
     * 총 상금을 기준으로 수익률(%)을 계산하고, 소수점 둘째 자리에서 반올림하여 반환한다.
     */
    public double getRateOfReturn(long amount) {
        double rate = (double) totalWinningAmount / amount * 100;
        return Math.round(rate * 100) / 100.0;
    }

    public Map<Rank, Integer> getWinningStatistics() {
        return winningStatistics;
    }
}
