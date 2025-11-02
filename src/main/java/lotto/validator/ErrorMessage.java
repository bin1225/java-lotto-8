package lotto.validator;


public enum ErrorMessage {

    //common
    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED("유틸리티 클래스는 인스턴스화할 수 없습니다."),

    //amount
    INVALID_UNIT("금액은 %d원 단위로 입력해주세요."),
    NEGATIVE_NUMBER("0보다 큰 값을 입력해주세요."),
    OUT_OF_RANGE("입력값이 허용 범위를 벗어났습니다. (%d ~ %d)"),

    //winningNumber
    INVALID_NUMBER_COUNT("당첨 번호는 %d개 입력해주세요."),
    INVALID_NUMBER_RANGE("당첨 번호는 %d부터 %d 사이의 숫자여야 합니다."),
    DUPLICATE_WINNING_NUMBER("당첨 번호에 중복된 숫자가 포함되어 있습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
