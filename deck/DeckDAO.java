package deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cards.*;

public class DeckDAO implements DeckDAOInterface {

    private File file;
    private Scanner scan;
    private String filepath;
    private String[] virus;
    private List<Card> deck;

    public DeckDAO(String filepath) throws FileNotFoundException{
        this.filepath = filepath;
        readFromFile();
        loadAllCardFromFile();
    }

    private void readFromFile() throws FileNotFoundException{
        file = new File(filepath);
        scan = new Scanner(file);
    }
    
    private void loadAllCardFromFile(){
        deck = new ArrayList<>();
        scan.next();
        while (scan.hasNext()) {
            virus = scan.next().split(",");
            Card card = new Card(virus);
            deck.add(card);

        }
    }
        
    @Override
    public List<Card> getDeck(){
        return deck;
    }

    @Override
    public Card getCard(int index) {
        return deck.get(index);
    }
  
}
