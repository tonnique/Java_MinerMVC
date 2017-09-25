package ru.antony;

import ru.antony.Controller.MinerController;
import ru.antony.Model.MinerModel;
import ru.antony.Controller.Console.ConsoleController;
import ru.antony.View.Console.ConsoleView;


/**
 * Created by Antony on 29.09.2016.
 */
public class LauncherConsole {

    public static void main(String[] args) {
        MinerModel model = new MinerModel();
        //ConsoleController controller = new ConsoleController(model);
        MinerController controller = new ConsoleController(model);
        new ConsoleView(model, controller);
    }
}
