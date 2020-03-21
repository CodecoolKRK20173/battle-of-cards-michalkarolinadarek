package com.Lechowicz.apps.aplication;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try{
            Dealer dealer = new Dealer();
            dealer.run();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Can't make clone of Card object. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("There is not enough cards to give to players. " + e.getMessage());
        }
    }
}