package lotto.config;

import java.util.Arrays;
import java.util.List;

public class LottoGameConfigValidator {
    private static final int BASIC_MONEY_UNIT = 1000;
    private static final int LOTTO_START_INCLUSIVE = 1;
    private static final int LOTTO_END_INCLUSIVE = 45;

    public Integer checkMoney(String userInputMoney) throws IllegalArgumentException {
        Integer money = getInteger(userInputMoney);
        if (money < BASIC_MONEY_UNIT) {
            throw new LottoValidationException("금액이 부족합니다.");
        }
        if (money % BASIC_MONEY_UNIT > 0) {
            throw new LottoValidationException("천 원 단위로 입력해주세요");
        }
        return money;
    }

    public void checkIfInRange(Integer lottoNumber) throws IllegalArgumentException {
        if (lottoNumber < LOTTO_START_INCLUSIVE || lottoNumber > LOTTO_END_INCLUSIVE) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> checkWinningNumbers(String winningNumbers) throws IllegalArgumentException {
        return Arrays.stream(winningNumbers.split(",")).map(Integer::valueOf).peek(this::checkIfInRange).toList();
    }

    public Integer checkBonusNumber(String rawBonusNumber, List<Integer> winningNumbers) {
        Integer bonusNumber = getInteger(rawBonusNumber);
        checkIfInRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new LottoValidationException("보너스 번호가 당첨 번호와 같습니다.");
        }
        return bonusNumber;
    }

    private Integer getInteger(String userInputNumber) {
        try {
            return Integer.valueOf(userInputNumber);
        } catch (NumberFormatException exception) {
            throw new LottoValidationException("정수가 아닌 잘못된 입력입니다.");
        }
    }
}
