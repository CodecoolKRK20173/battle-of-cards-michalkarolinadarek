package com.Lechowicz.apps.deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Lechowicz.apps.cards.Card;

public class DeckController {
    private DeckDAOInterface deckDAO;
    private List<Card> cardsFromDeckDAOUnique;
    private List<Card> cardsFromDeckDAOWithCopies;
    private List<ArrayList<Card>> cardsForPlayers;
    private Random random;
    private int turnSwitcher = 0;

    public DeckController(DeckDAOInterface dao) throws CloneNotSupportedException, FileNotFoundException {
        deckDAO = dao;
        cardsFromDeckDAOUnique = deckDAO.getDeck(); 
        cardsFromDeckDAOWithCopies = new ArrayList<>();
        cardsForPlayers = new ArrayList<ArrayList<Card>>();
        random = new Random();
        
        createDeckOfCopyCards();
    }

    private void createDeckOfCopyCards() throws CloneNotSupportedException {
        for(Card cardObject : cardsFromDeckDAOUnique){
            int numberOfCopy = cardObject.getType();
            for(int index = 0; index< numberOfCopy; index++){
                Card cardClone = (Card)cardObject.clone();
                cardsFromDeckDAOWithCopies.add(cardClone);
            }
        }
    }
   
    private Card getRandomCard(){
        ArrayList<Card> leftOvers = new ArrayList<>();
        for (Card card : cardsFromDeckDAOWithCopies){
            if(card.getHasOwner() == false){
                leftOvers.add(card);
            }
        }
        Card randomCardforPlayer = leftOvers.get(random.nextInt(leftOvers.size())); 

        return randomCardforPlayer;
    }
   
    public void drawCardsForPlayers(int countOfPLeayers, int numberOfCards){
        for(int index = 0; index< countOfPLeayers; index++){
            ArrayList<Card> playerList = new ArrayList<>();
            cardsForPlayers.add(playerList);
        }
        fillEmptyArraysWithCards(countOfPLeayers, numberOfCards);
    }

    private void fillEmptyArraysWithCards(int countOfPLeayers, int numberOfCards) {
        for(int index = 1; index <= countOfPLeayers * numberOfCards; index++){
            if(turnSwitcher < countOfPLeayers) {
                Card randomCardforPlayer = getRandomCard();
                markAsHasOwner(randomCardforPlayer);
                cardsForPlayers.get(turnSwitcher).add(randomCardforPlayer);
                turnSwitcher++;
            } else {
                turnSwitcher = 0;
                index--;
            } 
        }
    }
   
    private void markAsHasOwner(Card card){
        card.setHasOwner(true);
    }

    public List<ArrayList<Card>> getCardsForPlayers(){
        return cardsForPlayers;
    }
}