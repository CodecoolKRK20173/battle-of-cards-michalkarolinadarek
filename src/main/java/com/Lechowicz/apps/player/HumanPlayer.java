package com.Lechowicz.apps.player;

import java.util.ArrayList;

import com.Lechowicz.apps.cards.Card;

public class HumanPlayer extends AbstractPlayer {

    private String name;

    public HumanPlayer(String name){
        this.name = name;
        usedPile = new UsedPile();
        hand = new Hand(usedPile);
    }

    @Override
    public void setCardToHand(ArrayList<Card> cardForPlayer) {
        hand.setListOfCards(cardForPlayer);
    }

    @Override
    public void takeWonCard(Card card) {
        usedPile.addToUsedPile(card);
    }

    @Override
    public Card getTopCard() {
        Card returnCard = hand.removeCard();
        return returnCard;
    }

    @Override
    public int getUsedPileCount(){
        return usedPile.getCount();
    }

    @Override
    public String getName(){
        return name;
    }
}
