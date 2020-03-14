package player;

import java.util.List;

import cards.Card;

class HumanPlayer extends AbstractPlayer {

    private String name;

    public HumanPlayer(String name){
        this.name = name;
        usedPile = new UsedPile();
        hand = new Hand(usedPile);

    }

    @Override
    public void setCardToHand(List<Card> cardForPlayer) {
        hand.setListOfCards(cardForPlayer);
    }

    @Override
    public void takeWonCard(Card card) {
        usedPile.addToUsedPile(card);
    }

    @Override
    public Card getTopCard() {
        // TODO Auto-generated method stub
        return null;
    }

}
