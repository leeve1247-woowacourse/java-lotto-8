package lotto.config;

public class LottoValidationException extends IllegalArgumentException {
    private static final String SUFFIX = "[ERROR] ";

    public LottoValidationException(String string) {
        super(SUFFIX + string);
    }
}
