package com.Lechowicz.apps.aplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Lechowicz.apps.cards.*;
import com.Lechowicz.apps.deck.DeckDAOxml;
import com.Lechowicz.apps.interactions.View;
import com.Lechowicz.apps.deck.DeckController;
import com.Lechowicz.apps.interactions.*;
import com.Lechowicz.apps.player.AbstractPlayer;
import com.Lechowicz.apps.player.HumanPlayer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Dealer {
    private final int COUNT_OF_PLAYERS = 2;
    private final int COUNT_OF_ROUNDS = 10;
    private View view;
    private InputManager input;
    private DeckController deckController;
    private List<AbstractPlayer> playersList;
    private AbstractPlayer currentPlayer;
    private AbstractPlayer nextPlayer;
    private List<Card> tempStack;
    private List<Integer> gameResults;
    private AbstractPlayer winner;

    public Dealer()
            throws FileNotFoundException, CloneNotSupportedException, ParserConfigurationException, SAXException, IOException {
        initializeDeckController();
        view = new ViewTerminal();
        input = new InputManager();
        playersList = new ArrayList<>();
        tempStack = new ArrayList<>();
        gameResults = new ArrayList<Integer>();
    }

    private void initializeDeckController()
            throws FileNotFoundException, CloneNotSupportedException, ParserConfigurationException, SAXException, IOException {
        deckController = new DeckController(new DeckDAOxml("src/main/resources/virus.xml"));
    }
    
    public void run() {
        setPlayers(COUNT_OF_PLAYERS);
        prepareGame();
        playGameFor2Players();
    }

    private void setPlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            String name = input.askForName("Player " + i);
            AbstractPlayer player = new HumanPlayer(name);
            playersList.add(player);
        }
        currentPlayer = playersList.get(0);
        nextPlayer = playersList.get(1);
    }

    private void prepareGame() {
        deckController.drawCardsForPlayers(COUNT_OF_PLAYERS, COUNT_OF_ROUNDS);
        List<ArrayList<Card>> cardsForAllPlayers = deckController.getCardsForPlayers();
        int index = 0;
        for(ArrayList<Card> cardsForPlayer : cardsForAllPlayers) {
            playersList.get(index).setCardToHand(cardsForPlayer);
            index++;
        }
    }

    private void playGameFor2Players() {
        for (int round = 1; round <= COUNT_OF_ROUNDS; round++) {
            view.print(String.format("Round number %d! %s's turn to choose!", round, currentPlayer.getName()));
            Card currentPlayerCard = currentPlayer.getTopCard();
            Card nextPlayerCard = nextPlayer.getTopCard();
            view.print(currentPlayerCard);
            manageCardsFight(currentPlayerCard, nextPlayerCard);
            view.print(currentPlayerCard, nextPlayerCard);
            changeCurrentPlayer();
        }
        manageEndOfGame();
    }

    private void manageCardsFight(Card card1, Card card2){
        int statToCompare = input.askForStatToCompare();
        int comparisonResult = compareCards(card1, card2, statToCompare);
        manageCardsAfterRound(card1, card2, comparisonResult);
    }

    private int compareCards(Card card1, Card card2, int statToCompare) {
        int comparisonResult = 0;
        Comparator<Card> comparator;
        switch(statToCompare){
            case 1:
                comparator = new CardInfectvityComparator();
                comparisonResult = comparator.compare(card1, card2);
                break;
            case 2:
                comparator = new CardDeathsComparator();
                comparisonResult = comparator.compare(card1, card2);
                break;
            case 3:
                comparator = new CardIncubationComparator();
                comparisonResult = comparator.compare(card1, card2);
                break;
            case 4:
                comparator = new CardPainfulnessComparator();
                comparisonResult = comparator.compare(card1, card2);
                break;
            case 5:
                comparator = new CardPanicLevelComparator();
                comparisonResult = comparator.compare(card1, card2);
                break;    
        }
        return comparisonResult;
    }

    private void manageCardsAfterRound(Card card1, Card card2, int comparisonResult) {
        if (comparisonResult != 0) {
            AbstractPlayer roundWinner = (comparisonResult > 0) ? currentPlayer : nextPlayer;
            roundWinner.takeWonCard(card1);
            roundWinner.takeWonCard(card2);
            pullFromTempStack(roundWinner);
            view.print(String.format("%s won this round!", roundWinner.getName()));
        } else {
            tempStack.add(card1);
            tempStack.add(card2);
            view.print("It's a tie! These two cards will get to the winner of the next round.");
        }
    }
    
    private void manageEndOfGame() {
        view.print("The game is over!");
        getWinner();
        showGameResults();   
    }

    private void getWinner() {
        winner = playersList.get(0);
        for (AbstractPlayer player : playersList) {
            int result = player.getUsedPileCount();
            view.print(String.format("%s has %d points!", player.getName(), result));
            gameResults.add(result);
            if(result > winner.getUsedPileCount()) {
                winner = player;
            }
        }
    }

    private void showGameResults() {
        if (checkIfTie(gameResults)) {
            view.print("It's a tie!");
        } else {
            view.print(String.format("The winner of the game is %s! Congratulations!", winner.getName()));
        }
    }

    private boolean checkIfTie(List<Integer> listOfResults) {
        Set<Integer> setFromList = new HashSet<Integer>(listOfResults);
        return setFromList.size() < listOfResults.size();
    }

    private void pullFromTempStack(AbstractPlayer player){
        for(Card card: tempStack){
            player.takeWonCard(card);
        }
    }

    private void changeCurrentPlayer(){
        AbstractPlayer temp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = temp;
    }
}