import controller.GameController;
import controller.MenuController;
import view.GameContainer;

/**
 * The Main class serves as the entry point of the application.
 * This class follows the MVC (Model-View-Controller) architectural pattern
 * by initializing the Controller classes and the View class to manage the application state.
 * This class is done by Chan Ga Wai
 */
public class Main {
    public static void main(String[] args) {

        // Initialize the controller.GameController (Controller)
        GameController gameController = GameController.getInstance();

        // Initialize the controller.MenuController (Controller)
        MenuController menuController = new MenuController(gameController);

        // Initialize the view.GameContainer (View)
        GameContainer gameContainer = new GameContainer(gameController, menuController);

    }
}
