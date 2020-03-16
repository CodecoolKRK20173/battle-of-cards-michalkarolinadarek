package deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import cards.Card;
import interactions.*;

class Maintest {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        
        DeckDAO decker = new DeckDAO("deck/virus.csv");
        decker.getAllCardFromFile();

        Iterator<Card> it = new DeckIterator(decker);
        
        while(it.hasNext()){
            System.out.println(it.next().getName());
        }
        
        DeckController deckcontroller = new DeckController("deck/virus.csv");

        // deckcontroller.createDeckOfCopyCards();
        deckcontroller.getCardsForPlayers(6, 1);

        deckcontroller.DAOcards.get(0).setHasOwner(true);
        // System.out.println(deckcontroller.DAOcards.get(0).getName());
        // System.out.println(deckcontroller.DAOcards.get(0).getOwner()); //true
        // System.out.println(deckcontroller.deckOfCopyCards.get(0).getName());
        // System.out.println(deckcontroller.deckOfCopyCards.get(0).getOwner()); //false
        // Iterator<Card> it = new DeckIterator(decker);
        // while(it.hasNext()){
        //     System.out.println(it.next());
        // }
        // for(int i = 0; i < deckcontroller.deckOfCopyCards.size(); i++){
        //     //System.out.println("obj");
        //     View view = new View();
        //     view.print(deckcontroller.deckOfCopyCards.get(i));
        // }
        for (Card card : deckcontroller.cardsForPlayers) {
                View view = new View();
                view.print(card);
        }
    }
}