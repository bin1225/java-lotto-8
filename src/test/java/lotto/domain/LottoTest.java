package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_3개_일치하고_보너스_불일치하면_WinningResult_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 20, 30));
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        WinningResult result = lotto.getWinningResult(winningNumber);

        // then
        assertThat(result.matchCount()).isEqualTo(3);
        assertThat(result.matchBonus()).isFalse();
    }

    @Test
    void 당첨번호_4개_일치하고_보너스_번호가_포함되면_WinningResult_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        WinningResult result = lotto.getWinningResult(winningNumber);

        // then
        assertThat(result.matchCount()).isEqualTo(4);
        assertThat(result.matchBonus()).isTrue();
    }

    @Test
    void 당첨번호가_하나도_일치하지_않으면_0개_일치와_false를_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(10, 20, 30, 40, 41, 42));
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        WinningResult result = lotto.getWinningResult(winningNumber);

        // then
        assertThat(result.matchCount()).isZero();
        assertThat(result.matchBonus()).isFalse();
    }
}
