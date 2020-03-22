package com.Lechowicz.apps.deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.Lechowicz.apps.cards.*;

public class DeckDAOcsv implements DeckDAOInterface {

    private File file;
    private Scanner scan;
    private String filepath;
    private String[] virus;
    private List<Card> deck;

    public DeckDAOcsv(String filepath) throws FileNotFoundException {
        this.filepath = filepath;
        openFile();
        loadAllCardFromFile();
    }

    private void openFile() throws FileNotFoundException {
        file = new File(filepath);
        scan = new Scanner(file);
    }

    private void loadAllCardFromFile() {
        deck = new ArrayList<>();
        scan.next();
        while (scan.hasNext()) {
            virus = scan.next().split(",");
            Card card = new Card(virus);
            deck.add(card);
        }
    }

    private String prepareToSave(){
        String textToSave = "name,type,infected,deaths,incubation,painfulness,panic_level\n";
        for(Card card: deck){
            textToSave += String.format("%s,%s,%s,%s,%s,%s,%s\n",
            card.getName(), card.getType(), card.getInfectvity(),
            card.getDeaths(), card.getIncubation(), card.getPainfulness(), card.getPanicLevel());
        }
        return textToSave;
    }

    private void saveToFile(String contentToSave){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filepath);
            fileWriter.write(contentToSave);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public void updateDeck(List<Card> deck) {
        this.deck = deck;
        saveToFile(prepareToSave());
    }

    @Override
    public void updateCard(Card card, int index) {
        deck.remove(index);
        deck.add(index, card);
        saveToFile(prepareToSave());
    }

    @Override
    public void deleteCard(Card card) {
        deck.remove(card);
        saveToFile(prepareToSave());
    }

    @Override
    public void deleteCard(int index) {
        deck.remove(index);
        saveToFile(prepareToSave());
    }

    @Override
    public void deleteDeck() {
        deck.clear();
        saveToFile(prepareToSave());
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
        saveToFile(prepareToSave());
    }
  
}
