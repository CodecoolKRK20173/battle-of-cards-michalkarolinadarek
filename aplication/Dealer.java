package aplication;

import deck.DeckController;
import interactions.InputManager;
import interactions.View;

public class Dealer{
    View view;
    InputManager input;
    DeckController deckController;
    
    Dealer(){
        view = new View();
        input = new InputManager();
        // deckController = new DeckController();

        setPlayers(2);
        prepareGame();
        playGame();
    }
     
    private void setPlayers(int numberOfPlayers) {
        // Petla powtórzona x numberofPlayers:
            // spytanie o imię
            // utworzenie playera o danym imieniu
            // dodanie go do listy playerów
    }

    private void prepareGame() {
        // deckController.getCardsForPlayers(int countOfPLeayers, int numberOfCards)
        // for cardsSet in deckController.forDealer
            // i = index of player
            // players[i].setCardToHand(cardsSet)
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