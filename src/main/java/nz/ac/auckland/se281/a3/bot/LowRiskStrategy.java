package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements BotStrategy {

	@Override
	public Action decideAction(Hand hand) {
		// get the current score of the bot using hand parameter
		int score = hand.getScore();

		// if its score is >= 17, then return hold, otherwise return hit
		if (score >= 17) {
			return Action.HOLD;
		}
		return Action.HIT;

	}

	@Override
	public int makeABet() {
		// generate a random integer between 10 and 50
		float randomNumber = new Random().nextInt(50 - 10) + 10;

		// return the integer as the bet
		return (int) randomNumber;
	}
}
