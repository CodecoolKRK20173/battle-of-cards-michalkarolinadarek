package aplication;

import cards.Card;
import interactions.View;

public class Main{
    public static void main(String[] args){
        
        // Fragment do wywalenia, ale można dzięki niemu podejrzeć, jak drukują się karty:
        Card card = new Card();
        Card card2 = new Card();
        View view = new View();
        view.print(card);
        view.print(card, card2);
    }
}