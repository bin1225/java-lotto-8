package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoServiceTest {

    LottoService lottoService = new LottoService();

    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "3000, 3",
            "5000, 5"
    })
    void 구매금액에_맞는_개수의_로또를_반환한다(long amount, int expectedCount) {
        // when
        Lottos lottos = lottoService.issueLottos(amount);

        // then
        assertThat(lottos.getValue())
                .hasSize(expectedCount);
    }
}