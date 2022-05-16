package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TopWinnerStrategy implements DealerStrategy {

	@Override
	public Action decideAction(Hand dealerHand, Hand playerHand) {
		// check to see if the player is bust or the dealer has a higher score, or if
		// player is blackjack and dealer scores more than 17 if so, return HOLD
		if (playerHand.isBust() || (playerHand.isBlackJack() && dealerHand.getScore() >= 17)
				|| (playerHand.getScore() <= dealerHand.getScore())) {
			return Action.HOLD;
		}

		// otherwise return HIT
		return Action.HIT;
	}

	@Override
	public Hand getTargetPlayer(List<Player> players) {
		// initialise instance fields
		int bestNetWins = 0;
		Hand topWinner = null;

		// get the top player and return their hand
		for (Player player : players) {
			if ((player.getNumWins() - player.getNumLosses()) > bestNetWins) {
				bestNetWins = (player.getNumWins() - player.getNumLosses());
				topWinner = player.getHand();
			}
		}
		return topWinner;
	}

}
