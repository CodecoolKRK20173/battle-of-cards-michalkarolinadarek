package com.Lechowicz.apps.cards;

enum Attributes {
    
    INFECTVITY(2), DEATHS(3), INCUBATION(4), PAINFULNESS(5), PANIC_LEVEL(6);

    private int index;

    private Attributes(int index) { 
        this.index = index; 
    } 

    public int getIndex() {
        return index;
    }
} 