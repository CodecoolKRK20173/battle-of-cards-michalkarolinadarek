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
    View view;
    InputManager input;
    DeckController deckController;
    List<AbstractPlayer> playersList;
    List<Card> tempStack;
    
    AbstractPlayer currentPlayer;
    AbstractPlayer nextPlayer;

    final int COUNT_OF_PLAYERS = 2;
    final int COUNT_OF_ROUNDS = 10;

    public Dealer() throws CloneNotSupportedException {
        try {
            view = new View();
            input = new InputManager();
            playersList = new ArrayList<>();
            tempStack = new ArrayList<>();
            deckController = new DeckController("deck/virus.csv"); 

            setPlayers(COUNT_OF_PLAYERS);
            currentPlayer = playersList.get(0);
            nextPlayer = playersList.get(1);
            
            prepareGame();
            playGameFor2Players();
        
        } catch (FileNotFoundException e) {
            view.print("File not found. " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            view.print("Can't make clone of Card object. " + e.getMessage());
        }
    }
     
    private void setPlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++){
            String name = input.askForName("Player " + i);
            AbstractPlayer player = new HumanPlayer(name);
            playersList.add(player);
        }
    }

    private void prepareGame() {
        deckController.drawCardsForPlayers(COUNT_OF_PLAYERS, COUNT_OF_ROUNDS);
        List<ArrayList<Card>> temp = deckController.getCardsForPlayers();
        int index = 0;
        for(ArrayList<Card> cardsForPlayer : temp){
            playersList.get(index).setCardToHand(cardsForPlayer);
            index++;
        }
    }

    private void playGameFor2Players() {
        for (int round = 1; round <= COUNT_OF_ROUNDS ;round++) {
            view.print(String.format("Round number %d! %s's turn to choose!", round, currentPlayer.getName()));
            
            Card currentPlayerCard = currentPlayer.getTopCard();
            Card nextPlayerCard = nextPlayer.getTopCard();
            
            view.print(currentPlayerCard);
            int statToCompare = input.askForStatToCompare();
            view.print(currentPlayerCard, nextPlayerCard);

            compareCards(currentPlayerCard, nextPlayerCard, statToCompare);
            changeCurrentPlayer();
        }
        decideWhoWon();
    }

    private void compareCards(Card card1, Card card2, int statToCompare){
        int compareResult = 0;
        Comparator<Card> comp;
        switch(statToCompare){
            case 1:
                comp = new CardDeathsComparator();
                compareResult = comp.compare(card1, card2);
                break;
            case 2:
                comp = new CardIncubationComparator();
                compareResult = comp.compare(card1, card2);
                break;
            case 3:
                comp = new CardInfectvityComparator();
                compareResult = comp.compare(card1, card2);
                break;
            case 4:
                comp = new CardPainfulnessComparator();
                compareResult = comp.compare(card1, card2);
                break;
            case 5:
                comp = new CardPanicLevelComparator();
                compareResult = comp.compare(card1, card2);
                break;    
        }

        if(compareResult > 0){
            currentPlayer.takeWonCard(card1);
            currentPlayer.takeWonCard(card2);
            pullFromTempStack(currentPlayer);
            view.print("It won " + currentPlayer.getName());
        }
        else if(0 > compareResult){
            nextPlayer.takeWonCard(card1);
            nextPlayer.takeWonCard(card2);
            pullFromTempStack(nextPlayer);
            view.print("It won " + nextPlayer.getName());

        }
        else{
            tempStack.add(card1);
            tempStack.add(card2);
        }
    }

    void pullFromTempStack(AbstractPlayer player){
        for(Card card: tempStack){
            player.takeWonCard(card);
        }
    }
        // bierze dwie karty
        // wywołuje odpowiedni komparator na podstawie inta statToCompare (można zamknąć w oddzielną metodę)
        // na podstawie wygranej bierze kartę od przegranego i oddaje ją wygranemu, 
        // oraz przekłada wygranemu jego kartę do usedPile (też można zamknąć w oddzielną metodę)


    void changeCurrentPlayer(){
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

        

    // getOtherPlayer()

    



}