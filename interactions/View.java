package interactions;
import cards.Card;

public class View{
    public void print(String message) {
        System.out.println();
        System.out.println(" " + message);
    }
    
    public void print(Card card) {
        String[] lines = card.toString().split("\n");

        System.out.println();
        System.out.println(" " + "┌" + "─".repeat(32) + "┐");
        for (String line : lines) {
            System.out.println(String.format(" │ %-30s │" , line));
        }
        System.out.println(" " + "└" + "─".repeat(32) + "┘");
    }

    public void print(Card card1, Card card2) {
        String[] lines1 = card1.toString().split("\n");
        String[] lines2 = card2.toString().split("\n");

        System.out.println();
        System.out.println(" " + "┌" + "─".repeat(32) + "┐" + " " + "┌" + "─".repeat(32) + "┐");
        
        for (int i = 0; i < lines1.length; i++) {
            System.out.print(String.format(" │ %-30s │" , lines1[i]));
            System.out.println(String.format(" │ %-30s │" , lines2[i]));
        }
        System.out.println(" " + "└" + "─".repeat(32) + "┘" + " " + "└" + "─".repeat(32) + "┘");

    }

    public void clearScrean() {

    }
}