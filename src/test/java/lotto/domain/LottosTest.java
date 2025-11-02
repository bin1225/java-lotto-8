package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class LottosTest {

    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "3000, 3",
            "5000, 5"
    })
    void 구매금액에_맞는_개수의_로또를_반환한다(long amount, int expectedCount) {
        // when
        Lottos lottos = Lottos.generateLottos(amount);

        // then
        assertThat(lottos.asList())
                .hasSize(expectedCount);
    }

    @Test
    void 로또는_6개의_숫자로_구성된다() {
        //given
        Lottos lottos = Lottos.generateLottos(Lotto.PRICE * 5);

        //when & then
        lottos.asList().forEach(lotto ->
                assertThat(lotto.getNumbers()).hasSize(6)
        );
    }

    @Test
    void 로또는_1_에서_45_내의_숫자만_포함한다() {
        //given
        Lottos lottos = Lottos.generateLottos(Lotto.PRICE * 5);

        //when & then
        lottos.asList().forEach(lotto ->
                assertThat(lotto.getNumbers())
                        .allMatch(number -> number >= 1 && number <= 45)
        );
    }
}