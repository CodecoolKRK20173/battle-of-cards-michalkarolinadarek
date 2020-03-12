package interactions;
import cards.Card;

public class View{
    public void print(Card card) {
        String[] lines = card.toString().split("\n");
        System.out.println(java.util.Arrays.toString(lines));
    }
}