package lotto.view;

import java.util.function.Supplier;

public class InputHandler {

    private InputHandler() {
        throw new UnsupportedOperationException();
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
                System.out.println(e.getMessage());
            }
        }
    }
}
