package deck;

import java.util.Iterator;
import java.util.List;

import cards.Card;

class DeckIterator implements Iterator<Card>{

    private int index = 0;
    private List<Card> viruses;

    DeckIterator(DeckDAO deckdao){
        this.viruses = deckdao.getDeck();
    }
    
    @Override
    public boolean hasNext() {
        return index < viruses.size();
    }

    @Override
    public Card next() {
        return viruses.get(index++);
    }

}