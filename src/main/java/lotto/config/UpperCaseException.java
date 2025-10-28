package lotto.config;

public class UpperCaseException extends IllegalArgumentException {
    public UpperCaseException(String string) {
        super("[ERROR] " + string);
    }
}
