package lotto.config;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lotto.dto.Lotto;
import lotto.view.ConsoleView;

public class LottoGameConfigurator {
    private static final int BASIC_MONEY_UNIT = 1000;
    private static final int LOTTO_START_INCLUSIVE = 1;
    private static final int LOTTO_END_INCLUSIVE = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final LottoGameConfigValidator lottoGameConfigValidator = new LottoGameConfigValidator();
    private final ConsoleView consoleView;

    public LottoGameConfigurator(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public LottoGameConfig initGameConfig() {
        Integer money = initMoney();
        List<Lotto> lottos = initLottos(money);
        List<Integer> winningNumbers = initWinningNumbers();
        Integer bonusNumber = initBonusNumber(winningNumbers);

        return new LottoGameConfig(lottos, winningNumbers, bonusNumber, money);
    }

    private List<Lotto> initLottos(Integer money) {
        int count = money / BASIC_MONEY_UNIT;
        List<Lotto> lottos = generateLottos(count);
        consoleView.print(lottos);
        return lottos;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        while (count > 0) {
            List<Integer> integers = Randoms.pickUniqueNumbersInRange(LOTTO_START_INCLUSIVE, LOTTO_END_INCLUSIVE,
                    LOTTO_NUMBER_COUNT);
            lottos.add(new Lotto(integers));
            count = count - 1;
        }
        return lottos;
    }

    private Integer initBonusNumber(List<Integer> winningNumbers) {
        return getValidInput("보너스 번호를 입력해 주세요.",
                bonusNumber -> lottoGameConfigValidator.checkBonusNumber(bonusNumber, winningNumbers));
    }

    private List<Integer> initWinningNumbers() {
        return getValidInput("당첨 번호를 입력해 주세요.", lottoGameConfigValidator::checkWinningNumbers);
    }

    private Integer initMoney() {
        return getValidInput("구입금액을 입력해 주세요", lottoGameConfigValidator::checkMoney);
    }

    private <T> T getValidInput(String message, Function<String, T> validator) {
        while (true) {
            String input = consoleView.getUserInput(message);
            try {
                return validator.apply(input);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
