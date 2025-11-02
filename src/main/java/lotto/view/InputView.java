package lotto.view;

import static lotto.validator.ErrorMessage.UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.util.NumberParser;

public class InputView {

    private static final String READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String READ_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String READ_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String DELIMITER = ",";

    private InputView() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION_NOT_ALLOWED.getMessage());
    }

    public static long readPurchaseAmount() {
        System.out.println(READ_PURCHASE_AMOUNT_MESSAGE);
        String input = Console.readLine();
        return NumberParser.parseSingleLong(input);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println(READ_WINNING_NUMBER_MESSAGE);
        String input = Console.readLine();
        return NumberParser.parseIntegers(input, DELIMITER);
    }

    public static int readBonusNumber() {
        System.out.println(READ_BONUS_NUMBER_MESSAGE);
        String input = Console.readLine();
        return NumberParser.parseSingleInteger(input);
    }


}
