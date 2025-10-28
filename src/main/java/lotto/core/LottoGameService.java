package lotto.core;

import lotto.config.LottoGameConfig;
import lotto.dto.LottoGameRecord;

public class LottoGameService {
    public LottoGameService(LottoGameConfig lottoGameConfig) {
    }
    public LottoGameRecord play() {
        return new LottoGameRecord();
    }
}
