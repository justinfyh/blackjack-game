package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {

	@Override
	public Action play() {
		return null;
	}

	@Override
	public Action decideAction() {
		// TODO Auto-generated method stub
		float randomNumber = new Random().nextFloat();
		if (randomNumber <= 0.5) {
			return Action.HIT;
		}
		return Action.HOLD;
	}

	@Override
	public int makeABet() {
		// TODO Auto-generated method stub
		float randomNumber = new Random().nextInt(100 - 1) + 1;
		System.out.println((int) randomNumber);
		return (int) randomNumber;
	}

}
