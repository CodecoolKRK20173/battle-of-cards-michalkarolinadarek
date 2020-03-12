package cards;

import java.util.HashMap;

public class Card implements Comparable<Card> {
    

    final Integer ID_NAME = 0;
    final Integer ID_DEATHS = 1;
    final Integer ID_INCUBATION = 2;
    final Integer ID_INFECTVITY = 3;
    final Integer ID_PAINFULNESS = 4;
    final Integer ID_PANIC_LEVEL = 5;
    
    String name;
    private HashMap<String, Integer> parametersMap;
    Boolean hasOwner;
    Integer type;
    String[] titles = {"deaths", "incubation", "infectvity", "painfulness", "panicLevel"};


    public Card(String[] parametersCard){
        this.name = parametersCard[ID_NAME];

        
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
    
    public boolean equals(){

        return true;
    }

    public int hashCode(){
        return 0;
    }
}