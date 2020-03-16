package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

class UsedPile{
    List<Card> usedCards;
    Integer count;

    UsedPile(){
        usedCards = new ArrayList<Card>();
        count = 0;
    }

    void addToUsedPile(Card newCard){
        usedCards.add(newCard);
        count++;
    }

    int countOfCards(){
        return count;
    }
}