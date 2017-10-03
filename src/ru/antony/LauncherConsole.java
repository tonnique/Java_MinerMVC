package ru.antony;

import ru.antony.Controller.IMinerController;
import ru.antony.Model.IMinerModel;
import ru.antony.Model.MinerGameLogic;
import ru.antony.Controller.Console.ConsoleController;
import ru.antony.View.Console.ConsoleView;


/**
 * Created by Antony on 29.09.2016.
 */
public class LauncherConsole {

    public static void main(String[] args) {
        IMinerModel model = new MinerGameLogic();
        IMinerController controller = new ConsoleController(model);
        new ConsoleView(model, controller);
    }
}
