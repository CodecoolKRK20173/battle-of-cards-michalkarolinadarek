package interactions;

import cards.Card;

public abstract class View {
    public abstract void print(String message);

    public abstract void print(Card card) throws IndexOutOfBoundsException;

    public abstract void print(Card card1, Card card2) throws IndexOutOfBoundsException;

    public abstract void print(String[] list, String title);

    public abstract void clearScrean();
}