package interactions;

public class InputManager{
    private View view;
    
    public InputManager() {
        view = new View();
    }

    public String askForName(String intro) {
        String name = "";

        view.getStringInput(intro + "! What's your name?");
        while (name.length() < 1) {
            view.getStringInput(intro + "! What's your name?");
        }

        return name;
    }
}