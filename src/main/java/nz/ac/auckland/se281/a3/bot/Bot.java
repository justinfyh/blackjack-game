package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {

	private BotStrategy strategy;

	public Bot(String name, String botStrategyString) {
		super(name);
		this.strategy = botStrategyString;
	}

	public Action play() {
		return strategy.play();
	}

	@Override
	public Action decideAction(Hand hand) {
		return Action.HOLD;
	}

	@Override
	public int makeABet() {
		return 1;
	}

}
