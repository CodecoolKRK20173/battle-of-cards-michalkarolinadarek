package cards;
public class Card implements Comparable<Card>{

    public String toString() {
        // metoda "na brudno", na potrzeby testów View.print(card).
        String cardString = "Przykładowa Nazwa\nStat1\nStat2\nStat3\nStat3";
        return cardString;
    }

    @Override
    public int compareTo(Card o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}