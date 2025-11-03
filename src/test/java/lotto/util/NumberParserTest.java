package lotto.util;

import lotto.validator.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberParserTest {

    @Test
    void 숫자_리스트를_정상적으로_파싱한다() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> result = NumberParser.parseIntegers(input, ",");

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 공백과_쉼표가_포함된_입력도_정상적으로_파싱한다() {
        // given
        String input = " 1 , 2 , 3 ";

        // when
        List<Integer> result = NumberParser.parseIntegers(input, ",");

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 비어있는_입력이면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberParser.parseIntegers(input, ","))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_INPUT.getMessage());

        assertThatThrownBy(() -> NumberParser.parseSingleInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_INPUT.getMessage());

        assertThatThrownBy(() -> NumberParser.parseSingleLong(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,c", "1,2,a", "가,나,다"})
    void 숫자가_아닌_값이_포함되면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberParser.parseIntegers(input, ","))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    @Test
    void 단일_숫자를_정상적으로_파싱한다() {
        // given
        String input = "7";

        // when
        int resultInteger = NumberParser.parseSingleInteger(input);
        long resultLong = NumberParser.parseSingleLong(input);

        // then
        assertThat(resultInteger).isEqualTo(7);
        assertThat(resultLong).isEqualTo(7L);
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "abc", "1000a", "1_000"})
    void 단일_숫자가_아니면_예외가_발생한다(String input) {
        //when & then
        assertThatThrownBy(() -> NumberParser.parseSingleInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_NUMBER.getMessage());

        assertThatThrownBy(() -> NumberParser.parseSingleLong(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    @Test
    void long_범위를_초과한_입력은_예외가_발생한다() {
        // long 최대값 + 1 입력
        String tooLarge = "9223372036854775808"; // Long.MAX_VALUE + 1
        assertThatThrownBy(() -> NumberParser.parseSingleLong(tooLarge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE.getMessage(Long.MIN_VALUE, Long.MAX_VALUE));
    }
}
