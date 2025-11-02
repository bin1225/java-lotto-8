package lotto.validator;

import lotto.util.PurchaseAmountParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class PurchaseAmountParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 금액이_비어있으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.EMPTY_AMOUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000a", "1_000"})
    void 금액이_숫자가_아니면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    @Test
    @DisplayName("long 범위를 초과한 입력은 예외가 발생한다")
    void long_범위를_초과한_입력은_예외가_발생한다() {
        // long 최대값 + 1 입력
        String tooLarge = "9223372036854775808"; // Long.MAX_VALUE + 1
        assertThatThrownBy(() -> PurchaseAmountParser.parse(tooLarge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE.getMessage(Long.MIN_VALUE, Long.MAX_VALUE));
    }

    @Test
    void 금액이_0이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> PurchaseAmountParser.parse("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NEGATIVE_NUMBER.getMessage());
    }

    @Test
    void 금액이_음수이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> PurchaseAmountParser.parse("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    @Test
    void 금액이_로또_1개_가격_단위로_나누어지지_않으면_예외가_발생한다() {
        //given
        String input = "1500";
        long unit = lotto.domain.Lotto.PRICE;

        //when & then
        assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_UNIT.getMessage(unit));
    }

    @Test
    @DisplayName("유효한 금액이면 그대로 반환한다")
    void 유효한_금액이면_그대로_반환한다() {
        long result = PurchaseAmountParser.parse("8000");
        assertThat(result).isEqualTo(8000L);
    }
}
