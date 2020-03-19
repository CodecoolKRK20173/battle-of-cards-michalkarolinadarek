package cards;

enum Attributes {
    INFECTVITY(2), DEATHS(3), INCUBATION(4), PAINFULNESS(5), PANIC_LEVEL(6);

    private int index;

    public int getIndex(){
        return index;
    }
    private Attributes(int index) 
    { 
        this.index = index; 
    } 
} 