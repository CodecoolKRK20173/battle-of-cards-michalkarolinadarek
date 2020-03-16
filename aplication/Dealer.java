package aplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cards.Card;
import deck.DeckController;
import interactions.InputManager;
import interactions.View;
import player.AbstractPlayer;
import player.HumanPlayer;

public class Dealer {
    View view;
    InputManager input;
    DeckController deckController;
    List<AbstractPlayer> playersList;
    final int COUNT_OF_PLAYERS = 2;

    Dealer() throws FileNotFoundException, CloneNotSupportedException {
        view = new View();
        input = new InputManager();
        playersList = new ArrayList<>();
       try {
        deckController = new DeckController("deck/virus.csv"); 
       } catch (FileNotFoundException e) {
           view.print("File not found" + e.getMessage());
       }
       

        setPlayers(COUNT_OF_PLAYERS);
        prepareGame();
        playGame();
    }
     
    private void setPlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++){
            String name = input.askForName("Player " + i);
            AbstractPlayer player = new HumanPlayer(name);
            playersList.add(player);
        }
    }

    private void prepareGame() {
        deckController.getCardsForPlayers(COUNT_OF_PLAYERS, 10);
        List<ArrayList<Card>> temp = deckController.getForDealerList();
        int index = 0;
        for(ArrayList<Card> cardsForPlayer : temp){
            playersList.get(index).setCardToHand(cardsForPlayer);
            index++;
        }
    }

    private void playGame() {
        // while liczba kart u graczy > 0:
            // currentPlayerCard = currentPlayer.getTopCard
            // otherPlayerCard = getOtherPlayer.getTopCard
            // view.print(currentPlayerCard)
            // int statToCompare = input.askForStatToCompare()
            // compareCards(currentPlayerCard, otherPlayerCard, int statToCompare)
            // changeCurrentPlayer
        // decideWhoWon()

    }

    // compareCards(card1, card2, statToCompare)
        // bierze dwie karty
        // wywołuje odpowiedni komparator na podstawie inta statToCompare (można zamknąć w oddzielną metodę)
        // na podstawie wygranej bierze kartę od przegranego i oddaje ją wygranemu, 
        // oraz przekłada wygranemu jego kartę do usedPile (też można zamknąć w oddzielną metodę)


    // changeCurrentPlayer()
        

    // getOtherPlayer()

    



}