package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class TotalWinningResultTest {

    @Test
    void 당첨결과에_따라_수익률을_계산한다() {
        // given
        WinningResult first = new WinningResult(6, false); // Rank.FIRST
        WinningResult second = new WinningResult(5, true); // Rank.SECOND
        int purchaseAmount = 10000;

        //when
        TotalWinningResult totalWinningResult = TotalWinningResult.from(List.of(first, second));
        double rateOfReturn = totalWinningResult.getRateOfReturn(purchaseAmount);

        //then
        double expectedRate =
                Math.round(((Rank.FIRST.getReward() + Rank.SECOND.getReward()) / (double) purchaseAmount * 100) * 100)
                        / 100.0;
        assertThat(rateOfReturn).isEqualTo(expectedRate);
    }
}
