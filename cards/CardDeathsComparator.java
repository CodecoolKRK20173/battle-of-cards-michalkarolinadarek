package cards;
import java.util.Comparator;

public class CardDeathsComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getDeaths() - card2.getDeaths();
    }
    
}