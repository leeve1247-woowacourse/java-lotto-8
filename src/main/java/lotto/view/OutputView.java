package lotto.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.dto.Lotto;
import lotto.dto.LottoGameRecord;
import lotto.dto.Rank;

public class OutputView {
    public void print(LottoGameRecord gameRecord) {
        for (Map.Entry<Rank, Integer> entry : reverseSort(gameRecord)) {
            System.out.printf("%d개 일치", entry.getKey().getCondition());
            printIfBonusMatchingRequired(entry);
            System.out.printf(" (%,d원) - %d개\n", entry.getKey().getPrize(), entry.getValue());
        }
        System.out.printf("총 수익률은 %.1f%%입니다.", gameRecord.profitRate());
    }

    private ArrayList<Map.Entry<Rank, Integer>> reverseSort(LottoGameRecord gameRecord) {
        Map<Rank, Integer> rankIntegerMap = gameRecord.winnersByRank();
        ArrayList<Map.Entry<Rank, Integer>> entries = new ArrayList<>(rankIntegerMap.entrySet());
        entries.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        return entries;
    }

    private void printIfBonusMatchingRequired(Map.Entry<Rank, Integer> entry) {
        if (bonusMatchingRequired(entry)) {
            System.out.print(", 보너스 볼 일치");
        }
    }

    private boolean bonusMatchingRequired(Map.Entry<Rank, Integer> entry) {
        return entry.getKey().getBonus() == Boolean.TRUE;
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void print(List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            System.out.println(lotto.getNumbers());
        });
    }

    public void printLottosHeader(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }
}
