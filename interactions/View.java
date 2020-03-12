package interactions;

import java.util.Scanner;

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
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public String getStringInput(String message) {
        System.out.println(" " + message);
        System.out.print(" ");
        Scanner scannerFromUser = new Scanner(System.in);
        String input = scannerFromUser.nextLine();
        
        return input;
    }

    public int getIntInput(String message) {
        System.out.println(" " + message);
        System.out.print(" ");
        Scanner scannerFromUser = new Scanner(System.in);
        int input = scannerFromUser.nextInt();
        
        return input;
    }
}