package aplication;

import cards.Card;
import interactions.View;

public class Main{
    public static void main(String[] args){
        String[] ebola = {"Ebola", "1", "2", "5", "2", "5"};
        String[] sars = {"Sars", "6", "1", "9", "21", "56"};
        // Fragment do wywalenia, ale można dzięki niemu podejrzeć, jak drukują się karty:
        Card card = new Card(ebola);
        Card card2 = new Card(sars);
        View view = new View();
        view.print(card);
        view.print(card, card2);
        view.print(String.format("%d", card2.compareTo(card)));
    }
}