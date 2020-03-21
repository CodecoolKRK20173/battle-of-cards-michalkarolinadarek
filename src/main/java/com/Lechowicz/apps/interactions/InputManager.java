package com.Lechowicz.apps.interactions;

import java.util.Scanner;

public class InputManager {
    private ViewTerminal view;

    public InputManager() {
        view = new ViewTerminal();
    }

    private String getStringInput(String message) {
        view.print(message);
        view.printEmptyChar();
        Scanner scannerFromUser = new Scanner(System.in);
        String input = scannerFromUser.nextLine();
        
        return input;
    }

    private int getIntInput(String message) {
        view.print(message);
        view.printEmptyChar();
        int input = 0;
        Scanner scannerFromUser = new Scanner(System.in);

        while(!scannerFromUser.hasNextInt()){
            view.print("Wrong input! Please insert the integer number");
            scannerFromUser.next();
        }
        input = scannerFromUser.nextInt();
        
        return input;
    }

    public String askForName(String intro) {
        String name = "";
        name = getStringInput(intro + ", what's your name?");
        while (name.length() < 1) {
            name = getStringInput("Wrong input! What's your name?");
        }

        return name;
    }

    public int askForStatToCompare() {
        
        String[] listOfStats = new String[] {"Infected", "Deaths", "Incubation period", 
                                             "Painfullness", "Panic level"};
        view.print(listOfStats, "Available stats:");
        int statNumber = getIntInput("What do you want to compare? Type the number.");
        
        while (statNumber < 1 || statNumber > listOfStats.length) {
            statNumber = getIntInput(String.format("Wrong input! type the number between 1 and %d", 
                                                    listOfStats.length));
        }

        return statNumber;
    }
}