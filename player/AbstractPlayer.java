package player;

import cards.Card;

public abstract class AbstractPlayer {
    protected Hand hand;
    protected UsedPile usedPile;

    public abstract void takeCardToHand();

    public abstract void takeWonCard();

    public abstract Card getTopCard(); 
}
