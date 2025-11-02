package lotto.util;

import lotto.validator.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberInputParserTest {

    @Test
    void 숫자_리스트를_정상적으로_파싱한다() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> result = NumberInputParser.parseNumbers(input, ",");

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 공백과_쉼표가_포함된_입력도_정상적으로_파싱한다() {
        // given
        String input = " 1 , 2 , 3 ";

        // when
        List<Integer> result = NumberInputParser.parseNumbers(input, ",");

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 비어있는_입력이면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberInputParser.parseNumbers(input, ","))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_INPUT.getMessage());

        assertThatThrownBy(() -> NumberInputParser.parseSingleNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,c", "1,2,a", "가,나,다"})
    void 숫자가_아닌_값이_포함되면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberInputParser.parseNumbers(input, ","))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    @Test
    void 단일_숫자를_정상적으로_파싱한다() {
        // given
        String input = "7";

        // when
        int result = NumberInputParser.parseSingleNumber(input);

        // then
        assertThat(result).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가"})
    void 단일_숫자가_아니면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberInputParser.parseSingleNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_NUMBER.getMessage());
    }
}
