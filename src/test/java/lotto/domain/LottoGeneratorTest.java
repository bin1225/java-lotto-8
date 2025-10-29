package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    void 생성된_번호의_개수는_6개다() {
        // when
        List<Integer> numbers = LottoGenerator.generate();

        // then
        assertThat(numbers).hasSize(6);
    }

    @Test
    void 생성된_번호는_중복되지_않는다() {
        // when
        List<Integer> numbers = LottoGenerator.generate();

        // then
        assertThat(new HashSet<>(numbers)).hasSize(6);
    }

    @Test
    void 생성된_번호는_1_에서_45_범위_안에_포함된다() {
        // when
        List<Integer> numbers = LottoGenerator.generate();

        // then
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }
}