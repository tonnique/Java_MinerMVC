import Console.*;
import Model.MinerModel;


/**
 * Created by Antony on 29.09.2016.
 */
public class LauncherConsole {

    String userInput;

    public static void main(String[] args) {
        MinerModel model = new MinerModel();
        Console.ConsoleController controller = new ConsoleController(model);
        ConsoleView view = new ConsoleView(model, controller);



    }
}
