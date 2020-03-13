package deck;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

class DeckController {
    List<Card> DAOcards;
    List<Card> deckOfCards;

    DeckController(DeckDAO daodao){
    this.DAOcards = daodao.deck;  
    deckOfCards = new ArrayList<>();      
    }

    public void createDeckOfCards(){
            
    }
}