package lotto.config;

import java.util.List;
import lotto.dto.Lotto;

public record LottoGameConfig(List<Lotto> lottos, List<Integer> winningNumbers, Integer bonusNumber, Integer money) {
}
