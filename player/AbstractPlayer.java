package player;

import java.util.List;

import cards.Card;

public abstract class AbstractPlayer {
    protected Hand hand;
    protected UsedPile usedPile;

    public abstract void setCardToHand(List<Card> cardForPlayer);

    public abstract void takeWonCard(Card card);

    public abstract Card getTopCard();

    public int getCount(){
        return usedPile.countOfCards();
    }
}
