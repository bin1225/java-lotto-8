package lotto.domain;

public record WinningResult(int matchCount, boolean matchBonus, Rank rank) {

    public WinningResult(int matchCount, boolean matchBonus) {
        this(matchCount, matchBonus, Rank.of(matchCount, matchBonus));
    }

    public int getWinningAmount() {
        return rank.getReward();
    }
}
