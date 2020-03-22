package com.Lechowicz.apps.deck;

import com.Lechowicz.apps.cards.Card;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

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

    public DeckDAOxml(String filepath){
        deck = new ArrayList<>();
        openFileAndFillDeck(filepath);
    }
    private void openFileAndFillDeck(String filepath){
        try
        {
            //creating a constructor of file class and parsing an XML file
            File file = new File(filepath);
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writeXmlFile(ArrayList<Card> list){


        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("Card");
            doc.appendChild(root);

            Element Details = doc.createElement("name");
            root.appendChild(Details);
            Element Details2 = doc.createElement("type");
            root.appendChild(Details2);
            Element Details3 = doc.createElement("infected");
            root.appendChild(Details3);
            Element Details4 = doc.createElement("deaths");
            root.appendChild(Details4);
            Element Details5 = doc.createElement("incubation");
            root.appendChild(Details5);
            Element Details6 = doc.createElement("painfulness");
            root.appendChild(Details6);
            Element Details7 = doc.createElement("panic_level");
            root.appendChild(Details7);

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

    }

    @Override
    public void updateCard(Card card, int index) {

    }

    @Override
    public void deleteCard(Card card) {

    }

    @Override
    public void deleteCard(int index) {

    }

    @Override
    public void deleteDeck() {

    }

    @Override
    public void addCard(Card card) {

    }
}
