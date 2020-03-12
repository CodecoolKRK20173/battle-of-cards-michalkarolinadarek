package cards;

import java.util.HashMap;

class Card implements Comparable {
    

    final Integer ID_NAME = 0;
    final Integer ID_DEATHS = 1;
    final Integer ID_INCUBATION = 2;
    final Integer ID_INFECTVITY = 3;
    final Integer ID_PAINFULNESS = 4;
    final Integer ID_PANIC_LEVEL = 5;
    
    String name;
    HashMap<String, Integer> parametersMap;
    Boolean hasOwner;
    Integer type;


    Card(String[] parametersCard){
        this.name = parametersCard[ID_NAME];

        String[] titles = {"deaths", "incubation", "infectvity", "painfulness", "panicLevel"};
        this.parametersMap = new HashMap<String, Integer>();
        for(int index = 1; index < parametersCard.length ; index++){
            this.parametersMap.put(titles[index - 1], Integer.parseInt(parametersCard[index]));
        }
        this.hasOwner = false;
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}