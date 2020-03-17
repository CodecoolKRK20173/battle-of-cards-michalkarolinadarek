package interactions;

import java.util.Scanner;

public class InputManager {
    private View view;

    public InputManager() {
        view = new View();
    }

    private String getStringInput(String message) {
        System.out.println(" " + message);
        System.out.print(" ");
        Scanner scannerFromUser = new Scanner(System.in);
        String input = scannerFromUser.nextLine();
        
        return input;
    }

    private int getIntInput(String message) {
        System.out.println("\n " + message);
        System.out.print(" ");
        Scanner scannerFromUser = new Scanner(System.in);
        int input = scannerFromUser.nextInt();
        
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