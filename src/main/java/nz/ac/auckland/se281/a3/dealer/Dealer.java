package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;
import nz.ac.auckland.se281.a3.Player;

/**
 * 
 * You should change this class for Task 2
 *
 */

public class Dealer extends Participant {

	private DealerStrategy strategy;
	private List<Player> players;
	private Hand playerHand;
	private Hand dealerHand;

	public Dealer(String name, DealerStrategy strategy, List<Player> players) {
		super(name);
		this.strategy = strategy;
		this.players = players;
	}

	@Override
	public Action decideAction(Hand dealerHand) {

		this.dealerHand = dealerHand;

		playerHand = strategy.getTargetPlayer(players);

		return strategy.decideAction(dealerHand, playerHand);
	}

	public DealerStrategy getStrategy() {
		return strategy;
	}

	public Hand getDealerHand() {
		// TODO Auto-generated method stub
		return this.getHand();
	}

	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}

	public void setStrategy(DealerStrategy strategy) {
		this.strategy = strategy;
	}

}
