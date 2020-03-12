package aplication;

import cards.Card;
import interactions.View;

public class Main{
    public static void main(String[] args){
        Card card = new Card();
        View view = new View();
        view.print(card);
    }
}