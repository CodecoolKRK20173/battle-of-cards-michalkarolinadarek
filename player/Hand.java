package player;

import java.util.ArrayList;
import java.util.List;
import cards.Card;

class Hand {
    List<Card> cardsToUse;
    UsedPile usedPileCards;

    Hand(UsedPile usedPile){
        cardsToUse = new ArrayList<Card>();
        usedPileCards = usedPile;
    }


    void moveToUsedPile(Card card){
        usedPileCards.addToUsedPile(card);
    }

    Card removeCard(){
        Card returnCard = cardsToUse.get(0);
        cardsToUse.remove(returnCard);
        return returnCard;
    }

    void setListOfCards(List<Card> newListOfCard){
        cardsToUse = newListOfCard;
    }
}
