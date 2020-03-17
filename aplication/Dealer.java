package aplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    Dealer() throws CloneNotSupportedException {
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
            view.print("File not found" + e.getMessage());
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
        for(int round = 1; round <= COUNT_OF_ROUNDS ;round++){
            view.print("Current round: " + currentPlayer.getName());
            System.out.println("Ilość rund:" +round);
            Card currentPlayerCard = currentPlayer.getTopCard();
            Card nextPlayerCard = nextPlayer.getTopCard();
            view.print(currentPlayerCard);
            int statToCompare = input.askForStatToCompare();
            compareCards(currentPlayerCard, nextPlayerCard, statToCompare);
            changeCurrentPlayer();
        }

        if(currentPlayer.getUsedPileCount() > nextPlayer.getUsedPileCount()){
            view.print("Winner is " + currentPlayer.getName());
        }
        else{
            view.print("Winner is " + nextPlayer.getName());
        }
        // while liczba kart u graczy > 0:
            // currentPlayerCard = currentPlayer.getTopCard
            // otherPlayerCard = getOtherPlayer.getTopCard
            // view.print(currentPlayerCard)
            // int statToCompare = input.askForStatToCompare()

            // compareCards(currentPlayerCard, otherPlayerCard, int statToCompare)

            // changeCurrentPlayer
        // decideWhoWon()

    }
    // String[] listOfStats = new String[] {"Deaths", "Incubation period", "Infectivity", 
    //                                      "Painfullness", "Panic level"};
    void compareCards(Card card1, Card card2, int statToCompare){
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
        

    // getOtherPlayer()

    



}