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
        outputView.print(gameRecord);
    }

    public String getUserInput(String simpleLine) {
        outputView.print(simpleLine);
        return inputView.getUserInput();
    }
}
