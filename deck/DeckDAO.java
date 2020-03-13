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
    public String[] virus;
    public List<Card> deck;

    public DeckDAO(String filepath){
        this.filepath = filepath;
        deck = new ArrayList<>();
    }

    @Override
    public void getAllCardFromFile() throws FileNotFoundException {
        file = new File(filepath);
        scan = new Scanner(file);
        scan.next();
        while(scan.hasNext()){
            virus = scan.next().split(",");
            Card card = new Card(virus);
            deck.add(card);
            
        }
    }
  
}
