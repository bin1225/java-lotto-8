package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class LottosTest {

    @Test
    void 지정한_개수만큼_로또를_생성한다() {
        //given
        int generateCount = 5;

        //when
        Lottos lottos = Lottos.generateLottos(generateCount);

        //then
        assertThat(lottos.getValue()).hasSize(generateCount);
    }

    @Test
    void 로또는_6개의_숫자로_구성된다() {
        //given
        Lottos lottos = Lottos.generateLottos(5);

        //when & then
        lottos.getValue().forEach(lotto ->
                assertThat(lotto.getNumbers()).hasSize(6)
        );
    }

    @Test
    void 로또는_1_에서_45_내의_숫자만_포함한다() {
        //given
        Lottos lottos = Lottos.generateLottos(5);

        //when & then
        lottos.getValue().forEach(lotto ->
                assertThat(lotto.getNumbers())
                        .allMatch(number -> number >= 1 && number <= 45)
        );
    }
}