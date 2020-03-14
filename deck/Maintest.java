package deck;

import java.io.FileNotFoundException;
import java.util.Iterator;

import cards.Card;

class Maintest {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        DeckDAO decker = new DeckDAO("deck/virus.csv");
        DeckController deckcontroller = new DeckController(decker);
        deckcontroller.createDeckOfCards();
        decker.getAllCardFromFile();
        // Iterator<Card> it = new DeckIterator(decker);
        // while(it.hasNext()){
        //     System.out.println(it.next());
        // }
        for(int i = 0; i < deckcontroller.deckOfCopyCards.size(); i++){
            System.out.println("obj");
        }
    }
}