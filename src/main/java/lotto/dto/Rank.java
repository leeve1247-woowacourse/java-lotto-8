package lotto.dto;

public enum Rank {
    FIRST(2000000000, 6, null),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, null),
    FIFTH(5000, 3, null),
    UNRANKED(0, 0, null);

    private final Integer prize;
    private final Integer condition;
    private final Boolean bonus;

    Rank(Integer prize, Integer condition, Boolean bonus) {
        this.prize = prize;
        this.condition = condition;
        this.bonus = bonus;
    }

    public Integer getPrize() {
        return prize;
    }

    public Integer getCondition() {
        return condition;
    }

    public Boolean checkBonus(boolean hasBonus) {
        return bonus == null || bonus == hasBonus;
    }

    public Boolean getBonus() {
        return bonus;
    }
}
