package com.Lechowicz.apps.cards;

import java.util.HashMap;

import static com.Lechowicz.apps.cards.Attributes.*;

public class Card implements Comparable<Card>, Cloneable {

    private String name;
    private HashMap<String, Integer> parametersMap;
    private Boolean hasOwner;
    private Integer type;
    private String[] titles = {"name", "type", "infected", "deaths", "incubation", "painfulness", "panic level"};
    private String[] unitsForParameters = {"since 2010", "since 2010", "days", "%", "%"};

    public Card(String[] cardParameters){
        this.name = cardParameters[0];
        this.type = Integer.parseInt(cardParameters[1]); 
        hasOwner = false;
        loadParametersOfCard(cardParameters);
    }
    
    public HashMap<String, Integer> loadParametersOfCard(String[] cardParameters){
        parametersMap = new HashMap<String, Integer>();
        for(int index = 2; index < titles.length ; index++){
            parametersMap.put(titles[index], Integer.parseInt(cardParameters[index]));
        }
        return parametersMap;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Card secondCard) {
        if(this.name.compareTo(secondCard.name) >= 1) {
            return 1;
        } else if(this.name.compareTo(secondCard.name) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() throws IndexOutOfBoundsException {
        String output = name.toUpperCase() + "\n" + "\n";
        Integer indexUnitForParameters = 0;
        for(String key: titles){
            if(parametersMap.containsKey(key)) {
                String unit = unitsForParameters[indexUnitForParameters];
                output += String.format("%s (%s): %d\n", key, unit, parametersMap.get(key));
                indexUnitForParameters++;
            }
        }
        return output;
    }
    
    public boolean equals(Card secondCard){
        if(secondCard == null){
            return false;
        }
        if(!this.parametersMap.equals(secondCard.parametersMap)){
            return false;
        }
        if(!this.name.equals(secondCard.name)){
            return false;
        }
        if(!this.type.equals(secondCard.type)){
            return false;
        }
        return true;
    }

    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + name.length();
        for(String param :titles){
            if(parametersMap.containsKey(param))
                hash = 31 * hash + parametersMap.get(param);  
        }    
        return hash;
    }

    public String getName(){
        return this.name;
    }
    
    public int getDeaths(){
        return parametersMap.get(titles[Attributes.DEATHS.getIndex()]);
    }

    public int getIncubation(){
        return parametersMap.get(titles[Attributes.INCUBATION.getIndex()]);
    }

    public int getInfectvity(){
        return parametersMap.get(titles[Attributes.INFECTVITY.getIndex()]);
    }

    public int getPainfulness(){
        Integer integer = parametersMap.get(titles[Attributes.PAINFULNESS.getIndex()]);
        return integer;
    }

    public int getPanicLevel(){
        return parametersMap.get(titles[Attributes.PANIC_LEVEL.getIndex()]);
    }

    public int getType(){
        return type;
    }

    public void setHasOwner(boolean valueOfOwner){
        this.hasOwner = valueOfOwner;
    }

    public boolean getHasOwner(){
        return hasOwner;
    }
}
