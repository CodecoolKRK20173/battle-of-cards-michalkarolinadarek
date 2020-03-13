package cards;
import java.util.Comparator;

public class CardInfectvityComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getInfectvity() - card2.getInfectvity();
    }

}