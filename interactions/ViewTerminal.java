package interactions;

import cards.Card;

public class ViewTerminal extends View{
    @Override
    public void print(String message) {
        System.out.println();
        System.out.println(" " + message);
    }

    public void printEmptyChar() {
        System.out.print(" ");
    }
    
    @Override
    public void print(Card card) {
        String[] lines = card.toString().split("\n");

        System.out.println();
        System.out.println(" " + "┌" + "─".repeat(36) + "┐");
        for (String line : lines) {
            System.out.println(String.format(" │ %-34s │" , line));
        }
        System.out.println(" " + "└" + "─".repeat(36) + "┘");
    }

    @Override
    public void print(Card card1, Card card2) {
        String[] lines1 = card1.toString().split("\n");
        String[] lines2 = card2.toString().split("\n");

        System.out.println();
        System.out.println(" " + "┌" + "─".repeat(36) + "┐" + " " + "┌" + "─".repeat(36) + "┐");
        for (int i = 0; i < lines1.length; i++) {
            System.out.print(String.format(" │ %-34s │" , lines1[i]));
            System.out.println(String.format(" │ %-34s │" , lines2[i]));
        }
        System.out.println(" " + "└" + "─".repeat(36) + "┘" + " " + "└" + "─".repeat(36) + "┘");
    }

    @Override
    public void print(String[] list, String title) {
        System.out.println("\n " + title);
        for (int index = 1; index <= list.length; index++) {
            System.out.println(String.format(" %d. %s", index, list[(index - 1)]));
        }
    }

    public void clearScrean() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}