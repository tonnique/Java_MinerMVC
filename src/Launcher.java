import Controller.*;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Antony on 07.09.2016.
 */
public class Launcher {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                MinerModel model = new MinerModel();
                SwingMinerController controller = new SwingMinerController(model);

                MinerFrame frame = new MinerFrame(model, controller);

                //SettingsDialog frame = new SettingsDialog(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
