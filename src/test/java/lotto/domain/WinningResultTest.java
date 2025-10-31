package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningResultTest {

    @ParameterizedTest
    @CsvSource({
            // matchCount, matchBonus, expectedReward
            "6, false, 2000000000",   // 1등
            "5, true, 30000000",      // 2등
            "5, false, 1500000",      // 3등
            "4, false, 50000",        // 4등
            "3, false, 5000",         // 5등
            "2, false, 0"             // 낙첨
    })
    void 로또_당첨결과에_따라_상금을_계산한다(int matchCount, boolean matchBonus, long expectedReward) {
        // given
        WinningResult result = new WinningResult(matchCount, matchBonus);

        // when
        long actualReward = result.getWinningAmount();

        // then
        assertThat(actualReward).isEqualTo(expectedReward);
    }
}