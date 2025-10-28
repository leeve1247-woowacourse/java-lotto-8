package lotto.config;

import java.util.Arrays;
import java.util.List;

public class LottoGameConfigValidator {
    public void check(String userInputMoney) throws IllegalArgumentException {
        int money = Integer.parseInt(userInputMoney);
        if (money < 1000) {
            throw new IllegalArgumentException("금액이 부족합니다.");
        }
        if (money % 1000 > 0) {
            throw new IllegalArgumentException("천 원 단위로 입력해주세요");
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
        Integer bonusNumber = Integer.valueOf(rawBonusNumber);
        checkIfInRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
