package deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.Card;

class DeckController {
    DeckDAO deckdao;
    List<Card> DAOcards;
    List<Card> deckOfCopyCards;
    List<Card> cardsForPlayers;
    List<ArrayList<Card>> forDealer;
    Random random;
    int turnSwitcher = 0;

    
    DeckController(DeckDAO daodao){
        this.deckdao = daodao; 
        this.DAOcards = daodao.deck; 

        deckOfCopyCards = new ArrayList<>(); 
        cardsForPlayers = new ArrayList<>(); 
        cardsForPlayers.addAll(DAOcards);
        random = new Random();
    }


    public void createDeckOfCopyCards() throws CloneNotSupportedException {
        for(Card cardObject : DAOcards){
            int numberOfCopy = cardObject.getType();
            for(int i = 0; i< numberOfCopy; i++){
                Card cardClone = (Card)cardObject.clone();
                deckOfCopyCards.add(cardClone);
            }
        }
    }
   
   
    public Card getRandomCard(){
        ArrayList<Card> leftOvers = new ArrayList<>();
        for (Card card : cardsForPlayers){
            if(card.getOwner() == false){
                leftOvers.add(card);
            }
        }
         Card randomCardforPlayer = leftOvers.get(random.nextInt(leftOvers.size())); 
                if(randomCardforPlayer.getOwner()==true){
        }
        return randomCardforPlayer;
    }
   
   
    public void getCardsForPlayers(int countOfPLeayers, int numberOfCards){
        forDealer = new ArrayList<ArrayList<Card>>();
        cardsForPlayers.addAll(deckOfCopyCards);
        
        for(int i = 0; i< countOfPLeayers; i++){
            ArrayList<Card> playerList = new ArrayList<>();
            forDealer.add(playerList);
        }
        for(int i = 0; i <= countOfPLeayers * numberOfCards; i++){
            if(turnSwitcher < countOfPLeayers){
                Card randomCardforPlayer = getRandomCard();         //gets random card for Player
                markAsHasOwner(randomCardforPlayer);                // change hasOwner atribute to true
                forDealer.get(turnSwitcher).add(randomCardforPlayer);          //send random card to each player one by one
                turnSwitcher++;
            }else{turnSwitcher = 0;}
            
        }
    }
   
   
    public void markAsHasOwner(Card randomCardforPlayer){
        randomCardforPlayer.setOwner(true);
    }

}