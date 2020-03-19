package deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cards.*;

class DeckDAO implements DeckDAOInterface {

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
    
    @Override
    public List<Card> getDeck(){
        return deck;
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
  
}
