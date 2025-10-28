package lotto.config;

import lotto.dto.Lotto;

import java.util.List;

public record LottoGameConfig(List<Lotto> lottos, List<Integer> winningNumbers, Integer bonusNumber, Integer money) {
}
