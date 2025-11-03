package lotto.util;

import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import java.util.function.Consumer;
import java.util.function.Supplier;
import lotto.view.OutputView;

public class InputRetryHandler {

    private InputRetryHandler() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    /**
     * 잘못된 입력일 경우 예외 메시지를 출력하고 다시 입력받는다.
     * @param inputAction 실제 입력을 수행하는 함수
     * @return 입력 함수 호출 결과
     */
    public static <T> T retryUntilValid(Supplier<T> inputAction) {
        while (true) {
            try {
                return inputAction.get();
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    /**
     * 입력과 설정(set)을 하나의 작업 단위로 묶어 실행하며,
     * 예외 발생 시 전체 과정을 재시도
     *
     * @param <T> 입력 결과 타입
     * @param inputAction 입력을 수행하는 함수 (예: InputView::readWinningNumbers)
     * @param setAction   입력 결과를 이용해 설정을 수행하는 함수 (예: builder::setWinningNumbers)
     */
    public static <T> void retryUntilValid(Supplier<T> inputAction, Consumer<T> setAction) {
        while (true) {
            try {
                T input = inputAction.get();   // 입력 수행
                setAction.accept(input);       // 설정 수행
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }
}
