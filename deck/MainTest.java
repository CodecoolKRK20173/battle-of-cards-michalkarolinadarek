package deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cards.Card;

class MainTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        try {
            DeckDAO dao = new DeckDAO("resources/virus.csv");
            List<Card> cards = dao.getDeck();
            String[] newCard = {"newVirus", "3", "43242", "999","0000","2","23"};
            Card card = new Card(newCard);
            //Card card = cards.get(0);
            //Card card =(Card) cards.get(0).clone();
            //card.setType(1);
            //dao.deleteDeck();
            //dao.updateDeck(new ArrayList<Card>());
            dao.addCard(card);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}