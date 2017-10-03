package ru.antony;

import javax.swing.*;
import java.awt.*;

import ru.antony.Controller.*;
import ru.antony.Model.*;
import ru.antony.View.*;

/**
 * Created by Antony on 07.09.2016.
 */
public class Launcher {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                IMinerModel model = new MinerGameLogic();
                IMinerController controller = new SwingMinerController(model);
                MinerFrame frame = new MinerFrame(model, controller);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
