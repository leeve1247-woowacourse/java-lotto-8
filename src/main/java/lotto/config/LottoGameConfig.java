package lotto.config;

import lotto.dto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGameConfig {
    private List<Lotto> lottos = new ArrayList<>();
    private List<Number> winningNumbers = new ArrayList<>();
    private Number bonusNumber = null;

    public LottoGameConfig(List<Lotto> lottos, List<Number> winningNumbers, Number bonusNumber) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<Number> getWinningNumbers() {
        return winningNumbers;
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }
}
