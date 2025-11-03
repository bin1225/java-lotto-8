package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int reward;

    Rank(int matchCount, boolean matchBonus, int reward) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.reward = reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public int getReward() {
        return reward;
    }

    public static Rank of(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && matchBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return MISS;
    }

    /**
     * Rank를 선언된 순서의 역순으로 반환한다.
     */
    public static Iterable<Rank> reversedIterable() {
        List<Rank> list = Arrays.asList(values());
        Collections.reverse(list);
        return list;
    }
}
