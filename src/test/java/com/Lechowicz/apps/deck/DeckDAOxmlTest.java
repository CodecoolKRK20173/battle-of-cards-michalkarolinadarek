package com.Lechowicz.apps.deck;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

public class DeckDAOxmlTest {

    @org.junit.Test
    public void writeXmlFile() {
        DeckDAOxml dao;
        try {
            dao = new DeckDAOxml("src/main/resources/virus.xml");
            dao.writeXmlFile(dao.getDeck());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}