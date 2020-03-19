package deck;

import java.util.List;

import cards.Card;

public interface DeckDAOInterface {

     public List<Card> getDeck();

     public Card getCard(int index);
     
}