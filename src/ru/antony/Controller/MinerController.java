package ru.antony.Controller;

import ru.antony.Model.IGameSettings.*;
import ru.antony.Model.IMinerModel;
import ru.antony.View.IMinerView;

/**
 * Created by Antony on 22.09.2016.
 */
  public abstract class MinerController implements IMinerController{

    protected IMinerView view;
    protected IMinerModel model;

    public MinerController(IMinerModel m) {
        this.model = m;
    }

    public void setView(IMinerView v) {
        this.view = v;
    }

    public void startNewGame() {
        int[] gameSettings = getGameSettings();
        model.startNewGame(gameSettings[0], gameSettings[1], gameSettings[2]);
        view.addNewMineField();
    }

    /**
     * Method returns an array with Settings of the Game
     * @return an array of int with length of 3,
     * this array is representing game settings
     * index 0 - is minefield height
     * index 1 - is minefield width
     * index 2 - is amount of mines
     */
    public abstract int[] getGameSettings();

    public void LeftMouseClick(int row, int col) throws IllegalArgumentException {
        try {
            CellStatus cellStatus = model.getCellStatus(row, col);

            if (cellStatus == CellStatus.Opened ||
                    cellStatus == CellStatus.Flagged ||
                    model.isGameOver())
                return;
            else {
                model.openCell(row, col);
                view.updateMineField();
                if (model.isWin()) {
                    view.showWinMessage();
                } else if (model.isGameOver()) {
                    view.showGameOverMessage();
                }
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public void RightMouseClick(int row, int col) throws IllegalArgumentException {
        try {
            CellStatus cellStatus = model.getCellStatus(row, col);

            if (cellStatus == CellStatus.Opened || model.isGameOver()) return;
            else {
                model.nextCellMark(row, col);
                view.updateMineField();

                if (model.isWin())
                    view.showWinMessage();
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }
}