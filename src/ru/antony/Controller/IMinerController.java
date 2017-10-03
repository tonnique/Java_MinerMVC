package ru.antony.Controller;

import ru.antony.View.IMinerView;

/**
 * Created by Antony on 29.09.2017.
 */
public interface IMinerController {

    void setView(IMinerView view);

    void startNewGame();

    int[] getGameSettings();

    void LeftMouseClick(int row, int col) throws IllegalArgumentException;

    void RightMouseClick(int row, int col) throws IllegalArgumentException;
}
