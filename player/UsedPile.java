package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

class UsedPile{
    List<Card> usedCards;

    UsedPile(){
        usedCards = new ArrayList<Card>();
    }

    void addToUsedPile(Card newCard){
        usedCards.add(newCard);
    }
}