package ru.antony.Controller;

import ru.antony.Model.IGameSettings;
import ru.antony.Model.IMinerModel;
import ru.antony.View.IMinerView;
import ru.antony.View.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Antony on 25.09.2016.
 */
public class SwingMinerController extends MinerController implements MouseListener, ActionListener {

    SettingsDialog settings;

    public SwingMinerController(IMinerModel m) {super(m); }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = e.getY() / IMinerView.cellSize;
        int col = e.getX() / IMinerView.cellSize;

        //System.out.println(row + ", " + col);

        try {
            if (e.getButton() == MouseEvent.BUTTON1) {
                super.LeftMouseClick(row, col);
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                super.RightMouseClick(row, col);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem) {
            JMenuItem srcMI = (JMenuItem) e.getSource();
            processMenuClick(srcMI);
        }
        else if (e.getSource() instanceof  JButton) {
            JButton srcButton = (JButton) e.getSource();
            if (srcButton.getActionCommand() == "Restart") {
                super.startNewGame();
            }
        }
    }

    private void processMenuClick(JMenuItem mi) {

        switch (mi.getActionCommand()) {
            case "Settings":
                processSettingsMenu();
                break;
            case "newGame":
                super.startNewGame();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    private void processSettingsMenu() {
            if (settings == null)
                settings = new SettingsDialog((JFrame) view );

            settings.setVisible(true);
            if(settings.isUserConfirmed()) {
                startNewGame();
            }
    }


    public int[] getGameSettings() {
        if (settings == null ) {
            return new int[] {IGameSettings.BEGINNER_MINEFIELD_HEIGHT, IGameSettings.BEGINNER_MINEFIELD_WIDTH,
                    IGameSettings.BEGINNER_NUM_OF_BOMBS};
        }
        else {
            return new int[] {settings.getFieldHeight(),
                    settings.getFieldWidth(), settings.getBombsNumber()};
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
