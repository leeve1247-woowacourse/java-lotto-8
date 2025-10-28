package lotto.config;

import lotto.dto.Lotto;
import lotto.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class LottoGameConfigurator {
    private LottoGameConfigValidator lottoGameConfigValidator = new LottoGameConfigValidator();
    private ConsoleView consoleView;

    public LottoGameConfigurator(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public LottoGameConfig initGameConfig() {
        Number money = initMoney();
        List<Lotto> lottos = initLottos(money);
        List<Number> winningNumbers = initWinningNumbers();
        Number bonusNumber = initBonusNumber();

        return new LottoGameConfig(
                lottos,
                winningNumbers,
                bonusNumber
        );
    }

    private List<Lotto> initLottos(Number money) {
        return new ArrayList<>();
    }

    private Number initBonusNumber() {
        return 0;
    }

    private List<Number> initWinningNumbers() {
        return new ArrayList<>();
    }

    private Number initMoney() {
        return 0;
    }
}
