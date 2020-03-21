package com.Lechowicz.apps.cards;

import java.util.Comparator;

public class CardDeathsComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        int result = card1.getDeaths() - card2.getDeaths();
        if(result > 0)
            return 1;
        if(result < 0)
            return -1;
        return result;
    }
}