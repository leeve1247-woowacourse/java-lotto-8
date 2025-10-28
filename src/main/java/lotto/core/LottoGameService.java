package lotto.core;

import lotto.config.LottoGameConfig;
import lotto.dto.Lotto;
import lotto.dto.LottoGameRecord;
import lotto.dto.Rank;

import java.util.*;

public class LottoGameService {
    LottoGameConfig lottoGameConfig;

    public LottoGameService(LottoGameConfig lottoGameConfig) {
        this.lottoGameConfig = lottoGameConfig;
    }
    public LottoGameRecord play() {
        List<Lotto> lottos = lottoGameConfig.lottos();
        List<Integer> winningNumbers = lottoGameConfig.winningNumbers();
        Integer bonusNumber = lottoGameConfig.bonusNumber();
        Integer money = lottoGameConfig.money();

        Map<Rank, Integer> winnersByRank = new HashMap<>();
        for(Rank rank: Rank.values()) {
            winnersByRank.put(rank, 0);
        }

        Integer profit = 0;
        for (Lotto lotto : lottos) {
            List<Integer> sharedNumbers = new ArrayList<>(winningNumbers);
            sharedNumbers.retainAll(lotto.getNumbers());
            int sharedNumberCount = sharedNumbers.size();
            boolean containedBonusNumber = lotto.getNumbers().contains(bonusNumber);

            for(Rank rank: Rank.values()) {
                if (sharedNumberCount == rank.getCondition() && rank.checkBonus(containedBonusNumber)) {
                    winnersByRank.merge(rank, 1, Integer::sum);
                    profit += rank.getPrize();
                }
            }
        }

        Double profitRate = (double)profit/money*100;
        return new LottoGameRecord(winnersByRank, profitRate);
    }
}
