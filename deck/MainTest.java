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
            Card card = cards.get(0);
            //Card card =(Card) cards.get(0).clone();
            //card.setType(1);
            dao.deleteCard(0);
            //dao.updateDeck(new ArrayList<Card>());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}