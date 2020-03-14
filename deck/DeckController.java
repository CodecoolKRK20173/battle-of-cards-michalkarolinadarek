package deck;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

class DeckController {
    List<Card> DAOcards;
    List<Card> deckOfCopyCards;
    DeckDAO deckdao;

    DeckController(DeckDAO daodao){
        this.DAOcards = daodao.deck; 
        this.deckdao = daodao; 
        deckOfCopyCards = new ArrayList<>();      
    }

    public void createDeckOfCards() throws CloneNotSupportedException {
        for(Card cardObject : DAOcards){
            if(cardObject.getType() == 1){
                Card cardClone = (Card)cardObject.clone();
                deckOfCopyCards.add(cardClone);

            }
        }
    }
}