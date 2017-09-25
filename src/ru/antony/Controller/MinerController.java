package ru.antony.Controller;

import ru.antony.Model.Cell;
import ru.antony.Model.IGameSettings.*;
import ru.antony.Model.MinerModel;
import ru.antony.View.IMinerView;

/**
 * Created by Antony on 22.09.2016.
 */
public abstract class MinerController {

    protected IMinerView view;
    protected MinerModel model;

    public MinerController(MinerModel m) {
        this.model = m;
    }

    public void setView(IMinerView v) {
        this.view = v;
    }

    public void startNewGame() {
        int[] gameSettings = getGameSettings();
        //System.out.println(gameSettings[0] + ", " + gameSettings[1] + ", " + gameSettings[2]);
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
    protected abstract int[] getGameSettings();

    public void LeftMouseClick(int row, int col) {
        Cell cell = model.getCell(row, col);
        if (cell.getStatus() == CellStatus.Opened ||
            cell.getStatus() == CellStatus.Flagged ||
            model.isGameOver() )
            return;
        else {
            model.openCell(cell);
            view.updateMineField();
            if (model.isWin()) {
                view.showWinMessage();
            } else if (model.isGameOver()) {
                view.showGameOverMessage();
            }
        }
    }

    public void RightMouseClick(int row, int col) {
        Cell cell = model.getCell(row, col);

        if (cell.getStatus() == CellStatus.Opened || model.isGameOver()) return;
        else {
            model.nextCellMark(cell);
            view.updateMineField();

            if (model.isWin())
                view.showWinMessage();
        }
    }
}
