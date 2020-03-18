package deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.Card;

public class DeckController {
    DeckDAO deckDAO;
    List<Card> cardsFromDeckDAOUnique;
    List<Card> cardsFromDeckDAOWithCopies;
    List<ArrayList<Card>> cardsForPlayers;
    Random random;
    int turnSwitcher = 0;

    public DeckController(String filepath) throws CloneNotSupportedException, FileNotFoundException {
        deckDAO = new DeckDAO(filepath); 
        cardsFromDeckDAOUnique = deckDAO.getDeckDAO(); 
        cardsFromDeckDAOWithCopies = new ArrayList<>(); 
        random = new Random();
        
        createDeckOfCopyCards();
    }

    private void createDeckOfCopyCards() throws CloneNotSupportedException {
        for(Card cardObject : cardsFromDeckDAOUnique){
            int numberOfCopy = cardObject.getType();
            for(int i = 0; i< numberOfCopy; i++){
                Card cardClone = (Card)cardObject.clone();
                cardsFromDeckDAOWithCopies.add(cardClone);
            }
        }
    }
   
    private Card getRandomCard(){
        ArrayList<Card> leftOvers = new ArrayList<>();
        for (Card card : cardsFromDeckDAOWithCopies){
            if(card.getHasOwner() == false){
                leftOvers.add(card);
            }
        }
        Card randomCardforPlayer = leftOvers.get(random.nextInt(leftOvers.size())); 

        return randomCardforPlayer;
    }
   
   
    public void drawCardsForPlayers(int countOfPLeayers, int numberOfCards){
        cardsForPlayers = new ArrayList<ArrayList<Card>>();
        
        for(int i = 0; i< countOfPLeayers; i++){
            ArrayList<Card> playerList = new ArrayList<>();
            cardsForPlayers.add(playerList);
        }
        for(int i = 1; i <= countOfPLeayers * numberOfCards; i++){
            if(turnSwitcher < countOfPLeayers) {
                Card randomCardforPlayer = getRandomCard();
                markAsHasOwner(randomCardforPlayer);
                cardsForPlayers.get(turnSwitcher).add(randomCardforPlayer);
                turnSwitcher++;
            } else {
                turnSwitcher = 0;
                i--;
            } 
        }
    }
   
    private void markAsHasOwner(Card card){
        card.setHasOwner(true);
    }

    public List<ArrayList<Card>> getCardsForPlayers(){
        return cardsForPlayers;
    }
}