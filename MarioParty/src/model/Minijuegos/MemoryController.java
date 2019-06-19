package model.Minijuegos;

import java.util.Random;
import java.util.Vector;
import model.MarioPartyUtils;

public final class MemoryController {

	private Vector<String> pairs = new Vector<String>();
	private MemoryCard[] memoryCards;
	private int totalCards, counter, id1, id2, pairsCounter;
	private String card1Type, card2Type;

	public MemoryController() {
		totalCards = 6 * 3;
		memoryCards = new MemoryCard[6 * 3];
		fillPairsVector();
	}

	public void fillPairsVector() {
		for (int i = 0; i < (totalCards / 2); i++) {
			pairs.add(MarioPartyUtils.PERSONAJES[i]);
			pairs.add(MarioPartyUtils.PERSONAJES[i]);
		}
	}

	public String getRandomType() {
		Random rand = new Random();
		int randNum = rand.nextInt(pairs.size());
		String randType = pairs.elementAt(randNum);
		pairs.removeElementAt(randNum);
		return randType;
	}

	public MemoryCard[] generateMemoryCards() {
		for (int i = 0; i < totalCards; i++) {
			memoryCards[i] = new MemoryCard();
			memoryCards[i].setType(getRandomType());
			memoryCards[i].setId(i);
		}
		return memoryCards;
	}

	public boolean[] checkSelectedCards(MemoryCard card, MemoryCard[] gameCards) {
		boolean[] result = new boolean[2];
		counter++;
		card.showHideCard(1);
		if (counter % 2 != 0) //first selected card
		{
			card1Type = card.getType();
			id1 = card.getId();
			result[0] = false;
			result[1] = false;
			return result;
		} else //second card selected
		{
			card2Type = card.getType();
			id2 = card.getId();
			if (card1Type.equals(card2Type) && id1 != id2) //to check if the cards have the same type and are not the same card
			{
				card.setEnabled(false);
				gameCards[id1].setEnabled(false);
				pairsCounter++;
				result[0] = true;
				result[1] = false;
				return result;
			} else {
				card.showHideCard(0);
				gameCards[id1].showHideCard(0);
				result[0] = false;
				result[1] = true;
				return result;
			}
		}
	}

	public void hideImages(MemoryCard[] cards) {
		Thread hilo = new Thread() {
			public synchronized void run() {
				try {
					sleep(2000);
					for (int i = 0; i < totalCards; i++) {
						cards[i].setIcon(null);
						sleep(50);
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		};
		hilo.start();
	}

	public boolean winner() {
		boolean win = false;
		if (pairsCounter == totalCards / 2)
			win = true;
		return win;
	}

	public void showImagesToWinner(MemoryCard[] gameCards) {
		Thread hilo = new Thread() {
			public synchronized void run() {
				try {
					sleep(900);
					for (int i = (totalCards - 1); i > -1; i--) {
						gameCards[i].setEnabled(true);
						sleep(30);
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		};
		hilo.start();
	}
}
