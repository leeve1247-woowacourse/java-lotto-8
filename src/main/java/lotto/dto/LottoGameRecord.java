package lotto.dto;

import java.util.Map;

public record LottoGameRecord(Map<Rank, Integer> winnersByRank, Double profitRate) {
}
