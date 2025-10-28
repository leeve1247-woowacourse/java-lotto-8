package lotto.config;

import java.util.Arrays;
import java.util.List;

public class LottoGameConfigValidator {
    public void check(String userInputMoney) throws IllegalArgumentException {
        int money = getInteger(userInputMoney);
        if (money < 1000) {
            throw new UpperCaseException("금액이 부족합니다.");
        }
        if (money % 1000 > 0) {
            throw new UpperCaseException("천 원 단위로 입력해주세요");
        }
    }

    public void checkIfInRange(Integer a) throws IllegalArgumentException {
        if (a < 1 || a > 45) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> checkAndParse(String winningNumbers) throws IllegalArgumentException {
        return Arrays.stream(winningNumbers.split(",")).map(Integer::valueOf).peek(this::checkIfInRange).toList();
    }

    public void check(String rawBonusNumber, List<Integer> winningNumbers) {
        Integer bonusNumber = getInteger(rawBonusNumber);
        checkIfInRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new UpperCaseException("보너스 번호가 당첨 번호와 같습니다.");
        }
    }

    private Integer getInteger(String userInputNumber) {
        try {
            return Integer.valueOf(userInputNumber);
        } catch (NumberFormatException exception) {
            throw new UpperCaseException("정수가 아닌 잘못된 입력입니다.");
        }
    }
}
