package lotto.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.config.LottoGameConfig;
import lotto.dto.Lotto;
import lotto.dto.LottoGameRecord;
import lotto.dto.Rank;

public class LottoGameService {
    LottoGameConfig lottoGameConfig;

    public LottoGameService(LottoGameConfig lottoGameConfig) {
        this.lottoGameConfig = lottoGameConfig;
    }

    private static Map<Rank, Integer> initWinnersByRank() {
        Map<Rank, Integer> winnersByRank = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winnersByRank.put(rank, 0);
        }
        return winnersByRank;
    }

    public LottoGameRecord play() {
        Map<Rank, Integer> winnersByRank = initWinnersByRank();
        int profit = calculateProfit(winnersByRank);
        Double profitRate = calculateProfitRate(profit);
        return new LottoGameRecord(winnersByRank, profitRate);
    }

    private int calculateProfit(Map<Rank, Integer> winnersByRank) {
        Integer profit = 0;
        for (Lotto lotto : lottoGameConfig.lottos()) {
            Rank rank = determineRank(lotto);
            if (rank != Rank.UNRANKED) {
                profit += rank.getPrize();
                winnersByRank.merge(rank, 1, Integer::sum);
            }
        }
        return profit;
    }

    private double calculateProfitRate(int profit) {
        return (double) profit / lottoGameConfig.money() * 100;
    }

    private Rank determineRank(Lotto lotto) {
        int sharedNumberCount = getSharedNumbersSize(lotto);
        boolean containedBonusNumber = lotto.getNumbers().contains(lottoGameConfig.bonusNumber());
        for (Rank rank : Rank.values()) {
            if (sharedNumberCount == rank.getCondition() && rank.checkBonus(containedBonusNumber)) {
                return rank;
            }
        }
        return Rank.UNRANKED;
    }

    private int getSharedNumbersSize(Lotto lotto) {
        List<Integer> sharedNumbers = new ArrayList<>(lottoGameConfig.winningNumbers());
        sharedNumbers.retainAll(lotto.getNumbers());
        return sharedNumbers.size();
    }
}
