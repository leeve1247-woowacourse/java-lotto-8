package lotto.view;

import lotto.dto.LottoGameRecord;

public class ConsoleView {
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleView() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void print(LottoGameRecord gameRecord) {
    }
}
