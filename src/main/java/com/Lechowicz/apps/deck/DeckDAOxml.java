package com.Lechowicz.apps.deck;

import com.Lechowicz.apps.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DeckDAOxml implements  DeckDAOInterface{

    private String[] virus = new String[7];
    private List<Card> deck;

    public DeckDAOxml(String filepath) throws ParserConfigurationException, SAXException, IOException{
        deck = new ArrayList<>();
        openFile(filepath);
    }
    private void openFile(String filepath) throws ParserConfigurationException, SAXException, IOException{

        //creating a constructor of file class and parsing an XML file
        File file = new File(filepath);
        //an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        fillDeck(doc);

    }

    private void fillDeck(Document doc){
        NodeList nodeList = doc.getElementsByTagName("card");
        // nodeList is not iterable, so we are using for loop
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                virus[0] = eElement.getElementsByTagName("name").item(0).getTextContent();
                virus[1] = eElement.getElementsByTagName("type").item(0).getTextContent();
                virus[2] = eElement.getElementsByTagName("infected").item(0).getTextContent();
                virus[3] = eElement.getElementsByTagName("deaths").item(0).getTextContent();
                virus[4] = eElement.getElementsByTagName("incubation").item(0).getTextContent();
                virus[5] = eElement.getElementsByTagName("painfulness").item(0).getTextContent();
                virus[6] = eElement.getElementsByTagName("panic_level").item(0).getTextContent();
                Card card = new Card(virus);
                deck.add(card);

            }
        }
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        String indent = "4";
        TransformerFactory transFactory = TransformerFactory.newInstance();
        transFactory.setAttribute("indent-number", indent);
        Transformer aTransformer = transFactory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return aTransformer;
    }

    public void writeXmlFile(List<Card> listOfCards) throws TransformerException, ParserConfigurationException, IOException {

        Transformer aTransformer = getTransformer();
        DOMSource source = getDomStructure(listOfCards);

        FileWriter fileWriter = new FileWriter("src/main/resources/virus2.xml"); //replace filepath when done
        StreamResult result = new StreamResult(fileWriter);
        aTransformer.transform(source, result);
    }

    private HashMap<String, Integer> getParameters(List<String> titles, Card card){
        List<Integer> data;
        data = Arrays.asList(card.getType(), card.getInfectvity(), card.getDeaths(), card.getIncubation(), card.getPainfulness(), card.getPanicLevel());
        HashMap<String, Integer> param = new HashMap<>();
        Integer index = 0;
        for (String title : titles) {
            param.put(title, data.get(index++));
        }

        return param;
    }

    private DOMSource getDomStructure(List<Card> listOfCards) throws ParserConfigurationException {
        HashMap<String, Integer> parameters;
        List<String> titles = Arrays.asList("type", "infected", "deaths", "incubation", "painfulness", "panic_level");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        //Initialize root as viruses
        Element root = doc.createElement("viruses");
        doc.appendChild(root);

        for (Card card : listOfCards) {
            parameters = getParameters(titles, card);

            //Initialize single card
            Element eCard = doc.createElement("card");
            root.appendChild(eCard);
            //Initialize card name
            Element Details = doc.createElement("name");
            Details.appendChild(doc.createTextNode(String.format("%s", card.getName())));
            eCard.appendChild(Details);
            //Initialize card stat
            for(String title: titles){
                Details = doc.createElement(title);
                Details.appendChild(doc.createTextNode(String.format("%s", parameters.get(title))));
                eCard.appendChild(Details);
            }
        }
        return new DOMSource(doc);
    }

    @Override
    public List<Card> getDeck() {

        return deck;
    }

    @Override
    public Card getCard(int index) {

        return deck.get(index);
    }

    @Override
    public void updateDeck(List<Card> deck) {
        this.deck = deck;
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }
    }

    @Override
    public void updateCard(Card card, int index) {
        deck.remove(index);
        deck.add(index, card);
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCard(Card card) {
        deck.remove(card);
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCard(int index) {
        deck.remove(index);
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }

    }

    @Override
    public void deleteDeck() {
        deck.clear();
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
        try {
            writeXmlFile(deck);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // error with transform settings
            e.printStackTrace();
        } catch (TransformerException e) {
            //error with transform cause
            e.printStackTrace();
        }
    }
}
