package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.validator.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Test
    void 금액이_0이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NEGATIVE_NUMBER.getMessage());
    }

    @Test
    void 금액이_음수이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NEGATIVE_NUMBER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {1010, 1001, 20000200})
    void 금액이_로또_1개_가격_단위로_나누어지지_않으면_예외가_발생한다(long purchaseAmount) {
        //given
        long unit = Lotto.PRICE;

        //when & then
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_UNIT.getMessage(unit));
    }
}