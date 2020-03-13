package cards;

import java.util.Comparator;

public class CardPainfulnessComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getPainfulness() - card2.getPainfulness();
    }
    
}