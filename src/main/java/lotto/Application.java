package lotto;

import lotto.config.LottoGameConfig;
import lotto.config.LottoGameConfigurator;
import lotto.core.LottoGameService;
import lotto.dto.LottoGameRecord;
import lotto.view.ConsoleView;

public class Application {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();
        LottoGameConfigurator lottoGameConfigurator = new LottoGameConfigurator(consoleView);
        LottoGameConfig gameConfig = lottoGameConfigurator.initGameConfig();
        LottoGameService gameService = new LottoGameService(gameConfig);
        LottoGameRecord gameRecord = gameService.play();
        consoleView.print(gameRecord);
    }
}
