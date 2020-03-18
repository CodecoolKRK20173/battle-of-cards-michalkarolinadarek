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

    public DeckDAO(String filepath){
        this.filepath = filepath;
       
        if(readFromFile()){
            deck = new ArrayList<>();
            loadAllCardFromFile();
        }
       
    }
    
    @Override
    public List<Card> getDeck(){
        return deck;
    }

    private boolean readFromFile(){
        file = new File(filepath);
        try {
            scan = new Scanner(file);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    
    private void loadAllCardFromFile(){
        scan.next();
        while (scan.hasNext()) {
            virus = scan.next().split(",");
            Card card = new Card(virus);
            deck.add(card);

        }
    }
  
}
