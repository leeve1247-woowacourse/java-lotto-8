package lotto.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class LottoGameConfigValidatorTest {
    LottoGameConfigValidator lottoGameConfigValidator = new LottoGameConfigValidator();

    @Test
    void 잘못된_금액_입력_숫자가_아님() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkMoney("aaron"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_금액_입력_숫자가_아님_2() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkMoney("1000j"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_금액_입력_소수() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkMoney("2000.3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_금액_입력_천원_단위() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkMoney("2200"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_금액_공백_입력(){
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkWinningNumbers(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구분자가_쉼표가_아님() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkWinningNumbers("1-2-3-4-5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 공백_입력(){
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkWinningNumbers(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_범위를_벗어남() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkWinningNumbers("1,2,3,-65,27"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 소수를_입력한_당첨번호() {
        assertThatThrownBy(() -> lottoGameConfigValidator
                .checkWinningNumbers("1,2,3,3.5,27"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}