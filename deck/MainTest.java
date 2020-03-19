package deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import cards.Card;

class MainTest {
    public static void main(String[] args) {
        try {
            DeckDAO dao = new DeckDAO("resources/virus.csv");
            dao.updateDeck(new ArrayList<Card>());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}