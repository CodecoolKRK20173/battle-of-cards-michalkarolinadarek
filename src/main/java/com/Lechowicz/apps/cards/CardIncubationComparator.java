package com.Lechowicz.apps.cards;
import java.util.Comparator;

public class CardIncubationComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        int result = card1.getIncubation() - card2.getIncubation();
        if(result > 0)
            return 1;
        if(result < 0)
            return -1;
        return result;
    } 
}