package lotto.config;

import camp.nextstep.edu.missionutils.Randoms;
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
        Integer money = initMoney();
        List<Lotto> lottos = initLottos(money);
        List<Integer> winningNumbers = initWinningNumbers();
        Integer bonusNumber = initBonusNumber(winningNumbers);

        return new LottoGameConfig(
                lottos,
                winningNumbers,
                bonusNumber,
                money
        );
    }

    private List<Lotto> initLottos(Integer money) {
        List<Lotto> lottos = new ArrayList<>();
        int count = money / 1000;

        consoleView.printLottosHeader(count);

        while (count > 0) {
            List<Integer> integers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(integers));
            count = count - 1;
        }

        consoleView.print(lottos);

        return lottos;
    }

    private Integer initBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            String bonusNumber = consoleView.getUserInput("보너스 번호를 입력해 주세요.");
            try {
                lottoGameConfigValidator.check(bonusNumber, winningNumbers);
            } catch (Exception exception) {
                System.out.println("[Error]" + exception.getMessage());
                continue;
            }
            return Integer.valueOf(bonusNumber);
        }
    }

    private List<Integer> initWinningNumbers() {
        while (true) {
            String winningNumbers = consoleView.getUserInput("당첨 번호를 입력해 주세요.");
            try {
                return lottoGameConfigValidator.checkAndParse(winningNumbers);
            } catch (Exception exception) {
                System.out.println("[Error]" + exception.getMessage());
            }
        }
    }

    private Integer initMoney() {
        while (true) {
            String money = consoleView.getUserInput("구입금액을 입력해 주세요");
            try {
                lottoGameConfigValidator.check(money);
            } catch (Exception exception) {
                System.out.println("[Error]" + exception.getMessage());
                continue;
            }
            return Integer.valueOf(money);
        }
    }
}
