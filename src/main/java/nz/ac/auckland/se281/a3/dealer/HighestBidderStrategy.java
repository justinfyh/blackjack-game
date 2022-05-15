package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class HighestBidderStrategy implements DealerStrategy {

	@Override
	public Action decideAction(Hand dealerHand, Hand playerHand) {
		// TODO Auto-generated method stub

		if (playerHand.isBust()) {
			return Action.HOLD;
		}

		return null;
	}

}
