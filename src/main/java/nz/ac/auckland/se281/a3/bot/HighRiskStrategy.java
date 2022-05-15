package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class HighRiskStrategy implements BotStrategy {

	@Override
	public Action play() {
		return null;

	}

	@Override
	public Action decideAction(Hand hand) {
		// TODO Auto-generated method stub
		int score = hand.getScore();

		if (score >= 19) {
			return Action.HOLD;
		}
		return Action.HIT;
	}

	@Override
	public int makeABet() {
		// TODO Auto-generated method stub
		float randomNumber = new Random().nextInt(100 - 50) + 50;
		System.out.println((int) randomNumber);
		return (int) randomNumber;
	}

}
