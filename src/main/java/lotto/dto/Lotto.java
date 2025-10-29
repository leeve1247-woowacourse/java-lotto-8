package lotto.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.config.UpperCaseException;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new UpperCaseException("로또 번호는 6개여야 합니다.");
        }

        if (containsDuplicated(numbers)) {
            throw new UpperCaseException("중복하는 번호가 존재합니다.");
        }
    }

    private boolean containsDuplicated(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        set.addAll(numbers);
        return set.size() != numbers.size();
    }
}
