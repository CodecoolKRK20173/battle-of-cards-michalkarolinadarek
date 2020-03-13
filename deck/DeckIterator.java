package deck;

import java.util.Iterator;
import java.util.List;

import cards.Card;

class DeckIterator implements Iterator<Card>{

    int index = 0;
    List<Card> viruses;

    DeckIterator(DeckDAO deckdao){
        this.viruses = deckdao.deck;

    }
    @Override
    public boolean hasNext() {
      
        return index < viruses.size();
    }

    @Override
    public Card next() {
        // index++;
        return viruses.get(index++);
    }

}