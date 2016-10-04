package Controller;

import Model.IGameSettings;
import Model.MinerModel;
import View.MinerPanel;
import View.SettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Antony on 25.09.2016.
 */
public class SwingMinerController extends MinerController implements MouseListener, ActionListener {

    //JFrame frame;
    SettingsDialog settings;
    MinerModel model;

    //public SwingMinerController(JFrame f) { frame = f; }

    public SwingMinerController(MinerModel m) {super(m); model = m;}

    //@Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem) {
            JMenuItem srcMI = (JMenuItem) e.getSource();
            if (srcMI.getActionCommand() == "Settings") {
                if (settings  == null)
                    settings = new SettingsDialog((JFrame) view );

                settings.setVisible(true);
                if(settings.isUserConfirmed()) {
                    startNewGame();
                }
            }
        }
        else if (e.getSource() instanceof  JButton) {
            JButton srcButton = (JButton) e.getSource();
            if (srcButton.getActionCommand() == "Restart") {
                super.startNewGame();
            }
        }
    }

    protected int[] getGameSettings() {
        if (settings == null) {
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
    public void mouseReleased(MouseEvent e) {
        int row = e.getY() / MinerPanel.labelHeight;
        int col = e.getX() / MinerPanel.labelWidth;

        //System.out.println(row + ", " + col);

        if (e.getButton() == MouseEvent.BUTTON1) {
            super.LeftMouseClick(row, col);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            super.RightMouseClick(row, col);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
