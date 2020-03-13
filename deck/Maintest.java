package deck;

import java.io.FileNotFoundException;
import java.util.Iterator;

import cards.Card;

class Maintest {
    public static void main(String[] args) throws FileNotFoundException {
        DeckDAO decker = new DeckDAO("/home/dariusz/projekty/codecool/java/battle-of-cards-michalkarolinadarek/deck/virus.csv");
        decker.getAllCardFromFile();
        Iterator<Card> it = new DeckIterator(decker);
        while(it.hasNext()){
            System.out.println(it.next().getName());
        }
        
    }
}