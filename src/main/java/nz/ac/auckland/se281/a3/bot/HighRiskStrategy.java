package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class HighRiskStrategy implements BotStrategy {

	@Override
	public Action decideAction(Hand hand) {
		// get the current score of the bot using hand parameter
		int score = hand.getScore();

		// if its score is >= 19, then return hold, otherwise return hit
		if (score >= 19) {
			return Action.HOLD;
		}
		return Action.HIT;
	}

	@Override
	public int makeABet() {
		// generate a random integer between 50 and 100
		float randomNumber = new Random().nextInt(100 - 50) + 50;

		// return the integer as the bet
		return (int) randomNumber;
	}

}
