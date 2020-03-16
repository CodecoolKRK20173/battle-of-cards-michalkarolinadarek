package cards;

import java.util.Comparator;

public class CardPanicLevelComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getPanicLevel() - card2.getPanicLevel();
    }
    
}