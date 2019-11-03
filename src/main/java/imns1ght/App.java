package imns1ght;

public class App {
    public static void main(String[] args) {
        GameLoop game = new GameLoop(); // Initialize the game.

        while (!game.process_events());

    }
}
