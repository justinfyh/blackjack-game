package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {

	@Override
	public Action play() {
		return null;
	}

	@Override
	public Action decideAction(Hand hand) {
		// generate a random float number
		float randomNumber = new Random().nextFloat();

		// if that number is <= 0.5, then return hit, otherwise return hold
		if (randomNumber <= 0.5) {
			return Action.HIT;
		}
		return Action.HOLD;
	}

	@Override
	public int makeABet() {
		// generate a random integer between 1 and 100
		float randomNumber = new Random().nextInt(100 - 1) + 1;
//		System.out.println((int) randomNumber);
		// return the integer as the bet
		return (int) randomNumber;
	}

}
