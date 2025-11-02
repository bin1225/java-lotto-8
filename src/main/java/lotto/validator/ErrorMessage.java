package lotto.validator;


public enum ErrorMessage {

    /**
     * amount
     */
    EMPTY_AMOUNT("금액을 입력해주세요."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_UNIT("금액은 %d원 단위로 입력해주세요."),
    NEGATIVE_NUMBER("0보다 큰 값을 입력해주세요."),
    OUT_OF_RANGE("입력값이 허용 범위를 벗어났습니다. (%d ~ %d)");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
