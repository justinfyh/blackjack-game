package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TopWinnerStrategy implements DealerStrategy {

	@Override
	public Action decideAction(Hand dealerHand, Hand playerHand) {
		// TODO Auto-generated method stub

		if (playerHand.isBust() || (playerHand.isBlackJack() && dealerHand.getScore() >= 17)
				|| (playerHand.getScore() <= dealerHand.getScore())) {
			return Action.HOLD;
		}

		return Action.HIT;
	}

	@Override
	public Hand getTargetPlayer(List<Player> players) {
		int bestNetWins = 0;
		Hand topWinner = null;
		for (Player player : players) {
			if (player.getNetWins() > bestNetWins) {
				bestNetWins = player.getNetWins();
				topWinner = player.getHand();
			}
		}
		return topWinner;
	}

}
