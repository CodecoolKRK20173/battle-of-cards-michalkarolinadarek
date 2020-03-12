package cards;
public class Card implements Comparable<Card>{

    public String toString() {
        String cardString = "Przykładowa Nazwa\nStat1\nStat2\nStat3\nStat3";
        return cardString;
    }

    @Override
    public int compareTo(Card o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}