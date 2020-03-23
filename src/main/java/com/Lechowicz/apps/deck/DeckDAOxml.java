package com.Lechowicz.apps.deck;

import com.Lechowicz.apps.cards.Card;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
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
    public void writeXmlFile(List<Card> listOfCards){


        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            //Initialize root as viruses
            Element root = doc.createElement("viruses");
            doc.appendChild(root);
            for(Card card: listOfCards){
                //Initialize single card
                Element eCard = doc.createElement("card");
                root.appendChild(eCard);
                //Initialize card
                Element Details = doc.createElement("name");
                Details.appendChild(doc.createTextNode(card.getName()));
                eCard.appendChild(Details);
                Element Details2 = doc.createElement("type");
                Details2.appendChild(doc.createTextNode(String.format("%s", card.getType())));
                eCard.appendChild(Details2);
                Element Details3 = doc.createElement("infected");
                Details3.appendChild(doc.createTextNode(String.format("%s", card.getInfectvity())));
                eCard.appendChild(Details3);
                Element Details4 = doc.createElement("deaths");
                Details4.appendChild(doc.createTextNode(String.format("%s", card.getDeaths())));
                eCard.appendChild(Details4);
                Element Details5 = doc.createElement("incubation");
                Details5.appendChild(doc.createTextNode(String.format("%s", card.getIncubation())));
                eCard.appendChild(Details5);
                Element Details6 = doc.createElement("painfulness");
                Details6.appendChild(doc.createTextNode(String.format("%s", card.getPainfulness())));
                eCard.appendChild(Details6);
                Element Details7 = doc.createElement("panic_level");
                Details7.appendChild(doc.createTextNode(String.format("%s", card.getPanicLevel())));
                eCard.appendChild(Details7);
                //End initialize card
            }


            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer aTransformer = transFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            try{
                FileWriter fileWriter = new FileWriter("src/main/resources/virus2.xml");
                StreamResult result = new StreamResult(fileWriter);
                aTransformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException | TransformerConfigurationException | IOException e) {
            e.printStackTrace();
        }
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
        //saveToXML
    }

    @Override
    public void updateCard(Card card, int index) {
        deck.remove(index);
        deck.add(index, card);
        //saveToXML
    }

    @Override
    public void deleteCard(Card card) {
        deck.remove(card);
        //saveToXML
    }

    @Override
    public void deleteCard(int index) {
        deck.remove(index);
        //saveToXML
    }

    @Override
    public void deleteDeck() {
        deck.clear();
        //saveToXML
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
        //saveToXML
    }
}
