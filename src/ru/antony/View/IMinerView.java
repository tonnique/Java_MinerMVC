package ru.antony.View;

import java.awt.*;

/**
 * Created by Antony on 26.09.2016.
 */
public interface IMinerView {

    int cellSize = 25;

    Color COLOR_UNCHECKED = new Color(181, 230, 29);
    Color COLOR_OPENED = new Color(231, 235, 165);
    Color COLOR_MINED = new Color(255, 0, 0);

    Color[] colors = new Color[]{
            COLOR_OPENED,
            Color.BLUE,
            new Color(0, 100, 0),
            Color.RED,
            new Color(0, 0, 150),
            new Color(170, 0, 20),
            new Color(0, 150, 170),
            Color.BLACK,
            Color.GRAY
    };

    /**
     * Add new minefiled onto the View control in cases when the game is just started
     * or user has restarted the game
     */
    void addNewMineField();

    void showWinMessage();

    void showGameOverMessage();

    void updateMineField();



}
