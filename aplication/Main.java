package aplication;

import java.util.Comparator;

import cards.Card;
import cards.CardDeathsComparator;
import cards.CardIncubationComparator;
import interactions.View;

public class Main{
    public static void main(String[] args){
        String[] ebola = {"Ebola", "1", "2", "5", "2", "5"};
        String[] sars = {"Sars", "6", "1", "9", "21", "56"};
        // Fragment do wywalenia, ale można dzięki niemu podejrzeć, jak drukują się karty:
        Card card1 = new Card(ebola);
        Card card2 = new Card(sars);
        Card card3 = new Card(sars);
        View view = new View();
        view.print(card1);
        view.print(card1, card2);
        view.print(String.format("%d", card2.compareTo(card1)));

        view.print(String.format("%d", card2.getPanicLevel()));
        view.print(String.format("%b", card2.equals(null)));

        view.print(String.format("%d", card1.hashCode()));
        view.print(String.format("%d", card2.hashCode()));
        view.print(String.format("%d", card3.hashCode()));

        Comparator<Card> comp = new CardDeathsComparator();
        comp = new CardIncubationComparator();
        view.print(String.format("%d", comp.compare(card1, card2)));

    }
}