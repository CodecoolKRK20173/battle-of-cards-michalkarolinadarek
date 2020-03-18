package aplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cards.*;
import deck.DeckController;
import interactions.*;
import player.AbstractPlayer;
import player.HumanPlayer;

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

    public Dealer() {
        view = new View();
        input = new InputManager();
        playersList = new ArrayList<>();
        tempStack = new ArrayList<>();
    }
    
    public void run() {
        try {
            deckController = new DeckController("deck/virus.csv");
            setPlayers(COUNT_OF_PLAYERS);
            prepareGame();
            playGameFor2Players();
        } catch (FileNotFoundException e) {
            view.print("File not found. " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            view.print("Can't make clone of Card object. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            view.print("There is not enough cards to give to players. " + e.getMessage());
        }
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
            compareCards(currentPlayerCard, nextPlayerCard);
            view.print(currentPlayerCard, nextPlayerCard);
            changeCurrentPlayer();
        }
        decideWhoWon();
    }

    private void compareCards(Card card1, Card card2){
        int statToCompare = input.askForStatToCompare();
        int comparisonResult = getRightComparator(card1, card2, statToCompare);
        manageCardsAfterComparison(card1, card2, comparisonResult);
    }

    private int getRightComparator(Card card1, Card card2, int statToCompare) {
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

    private void manageCardsAfterComparison(Card card1, Card card2, int comparisonResult) {
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

    private void decideWhoWon() {
        AbstractPlayer winner = playersList.get(0);
        List<Integer> allResults = new ArrayList<Integer>();
        view.print("The game is over!");

        for (AbstractPlayer player : playersList) {
            int result = player.getUsedPileCount();
            view.print(String.format("%s has %d points!", player.getName(), result));
            allResults.add(result);
            
            if(result > winner.getUsedPileCount()) {
                winner = player;
            }
        }
        if (checkIfTie(allResults)) {
            view.print("It's a tie!");
        } else {
            view.print(String.format("The winner of the game is %s! Congratulations!", winner.getName()));
        }
    }

    private boolean checkIfTie(List<Integer> listOfResults) {
        Set<Integer> setFromList = new HashSet<Integer>(listOfResults);
        boolean hasDuplicates = (setFromList.size() < listOfResults.size()) ? true : false;
        
        return hasDuplicates;
    }
}