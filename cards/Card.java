package cards;

import java.util.HashMap;

public class Card implements Comparable<Card> {
    

    final Integer ID_DEATHS = 0;
    final Integer ID_INCUBATION = 1;
    final Integer ID_INFECTVITY = 2;
    final Integer ID_PAINFULNESS = 3;
    final Integer ID_PANIC_LEVEL = 4;
    
    String name;
    HashMap<String, Integer> parametersMap;
    Boolean hasOwner;
    Integer type;
    String[] titles = {"deaths", "incubation", "infectvity", "painfulness", "panicLevel"};


    public Card(String[] parametersCard){
        this.name = parametersCard[0]; // The first value is a name
        this.type = 1; 

        
        this.parametersMap = new HashMap<String, Integer>();
        for(int index = 1; index < parametersCard.length ; index++){
            this.parametersMap.put(titles[index - 1], Integer.parseInt(parametersCard[index]));
        }
        this.hasOwner = false;
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

    public String toString(){
        String output = name + "\n";

        for(String key: titles){
            output += parametersMap.get(key) + "\n";
        }
        return output;
    }
    
    public boolean equals(Card secondCard){
        if(!this.parametersMap.equals(secondCard.parametersMap)){
            return false;
        }
        if(!this.name.equals(secondCard.name) ? true : false){
            return false;
        }
        if(!this.type.equals(secondCard.type) ? true : false){
            return false;
        }
        return true;
    }

    public int hashCode(){
        return 0;
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
}
