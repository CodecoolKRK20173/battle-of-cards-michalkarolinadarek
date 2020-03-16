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

    // Czy potrzebujemy count jako oddzielne pole? Nie wystarczy usedCards.size()?
    int countOfCards(){
        return count;
    }
}