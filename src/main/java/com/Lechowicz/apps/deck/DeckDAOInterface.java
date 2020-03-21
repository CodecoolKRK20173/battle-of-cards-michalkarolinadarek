package com.Lechowicz.apps.deck;

import java.util.List;

import com.Lechowicz.apps.cards.Card;

public interface DeckDAOInterface {

     public List<Card> getDeck();

     public Card getCard(int index);

     public void updateDeck(List<Card> deck);

     public void updateCard(Card card, int index);

     public void deleteCard(Card card);

     public void deleteCard(int index);

     public void deleteDeck();

     public void addCard(Card card);
     
}
