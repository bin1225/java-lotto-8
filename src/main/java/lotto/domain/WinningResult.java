package lotto.domain;

public class WinningResult {

    private final int matchCount;
    private final boolean matchBonus;
    private final Rank rank;

    public WinningResult(int matchCount, boolean matchBonus) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        rank = Rank.of(matchCount, matchBonus);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public int getWinningAmount() {
        return rank.getReward();
    }
}
