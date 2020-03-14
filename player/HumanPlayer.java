package player;

import java.util.List;

import cards.Card;

class HumanPlayer extends AbstractPlayer {

    private String name;

    public HumanPlayer(String name){
        this.name = name;
    }

    @Override
    public void setCardToHand(List<Card> cardForPlayer) {
        hand.setListOfCards(cardForPlayer);
    }

    @Override
    public void takeWonCard() {
        // TODO Auto-generated method stub

    }

    @Override
    public Card getTopCard() {
        // TODO Auto-generated method stub
        return null;
    }

}