package cards;

import java.util.HashMap;

public class Card implements Comparable<Card>,Cloneable {
    
    final Integer ID_INFECTVITY = 2;
    final Integer ID_DEATHS = 3;
    final Integer ID_INCUBATION = 4;
    final Integer ID_PAINFULNESS = 5;
    final Integer ID_PANIC_LEVEL = 6;
    
    private String name;
    HashMap<String, Integer> parametersMap;
    private Boolean hasOwner;
    private Integer type;
    private String[] titles = {"name", "type", "infected", "deaths", "incubation", "painfulness", "panic level"};
    private String[] unitsForPArameters = {"since 2010", "since 2010", "days", "%", "%"};


    public Card(String[] cardParameters){
        this.name = cardParameters[0]; // The first value is a name
        this.type = Integer.parseInt(cardParameters[1]); // The second value is a type

        this.parametersMap = new HashMap<String, Integer>();
        for(int index = 2; index < titles.length ; index++){
            this.parametersMap.put(titles[index], Integer.parseInt(cardParameters[index]));
        }
        this.hasOwner = false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Card secondCard) {
        if(this.name.compareTo(secondCard.name) >= 1){
            return 1;
        }
        else if(this.name.compareTo(secondCard.name) == 0){
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() throws IndexOutOfBoundsException {
        String output = name.toUpperCase() + "\n" + "\n";
        Integer indexUnitForParameters = 0;
        for(String key: titles){
            if(parametersMap.containsKey(key)) {
                String unit = unitsForPArameters[indexUnitForParameters];
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
        return parametersMap.get(titles[ID_DEATHS]);
    }

    public int getIncubation(){
        return parametersMap.get(titles[ID_INCUBATION]);
    }

    public int getInfectvity(){
        return parametersMap.get(titles[ID_INFECTVITY]);
    }

    public int getPainfulness(){
        return parametersMap.get(titles[ID_PAINFULNESS]);
    }

    public int getPanicLevel(){
        return parametersMap.get(titles[ID_PANIC_LEVEL]);
    }

    public int getType(){
        return type;
    }

    public void setType(int valueOfType){
        this.type = valueOfType;
    }

    public void setHasOwner(boolean valueOfOwner){
        this.hasOwner = valueOfOwner;
    }

    public boolean getHasOwner(){
        return hasOwner;
    }

}
