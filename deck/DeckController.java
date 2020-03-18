package deck;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.Card;

public class DeckController {
    private DeckDAO deckDAO;
    private List<Card> cardsFromDeckDAOUnique;
    private List<Card> cardsFromDeckDAOWithCopies;
    private List<ArrayList<Card>> cardsForPlayers;
    private Random random;
    private int turnSwitcher = 0;

    public DeckController(String filepath) throws CloneNotSupportedException, FileNotFoundException {
        deckDAO = new DeckDAO(filepath); 
        if(deckDAO.getDeck() == null){
            throw new FileNotFoundException(filepath);
        }
        cardsFromDeckDAOUnique = deckDAO.getDeck(); 
        cardsFromDeckDAOWithCopies = new ArrayList<>(); 
        random = new Random();
        
        createDeckOfCopyCards();
    }

    private void createDeckOfCopyCards() throws CloneNotSupportedException {
        for(Card cardObject : cardsFromDeckDAOUnique){
            int numberOfCopy = cardObject.getType();
            for(int index = 0; index< numberOfCopy; index++){
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
        
        for(int index = 0; index< countOfPLeayers; index++){
            ArrayList<Card> playerList = new ArrayList<>();
            cardsForPlayers.add(playerList);
        }
        for(int index = 1; index <= countOfPLeayers * numberOfCards; index++){
            if(turnSwitcher < countOfPLeayers) {
                Card randomCardforPlayer = getRandomCard();
                markAsHasOwner(randomCardforPlayer);
                cardsForPlayers.get(turnSwitcher).add(randomCardforPlayer);
                turnSwitcher++;
            } else {
                turnSwitcher = 0;
                index--;
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