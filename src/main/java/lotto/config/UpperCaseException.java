package lotto.config;

public class UpperCaseException extends IllegalArgumentException {
    private static final String SUFFIX = "[ERROR] ";

    public UpperCaseException(String string) {
        super(SUFFIX + string);
    }
}
