package cards;
import java.util.Comparator;

public class CardIncubationComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getIncubation() - card2.getIncubation();
    }
    
}