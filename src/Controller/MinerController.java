package Controller;

import Model.Cell;
import Model.CellStatus;
import Model.MinerModel;
import View.IMinerView;


/**
 * Created by Antony on 22.09.2016.
 */
public abstract class MinerController {

    protected IMinerView view;
    private MinerModel model;

    public MinerController(MinerModel m) {
        this.model = m;
    }

    public void setView(IMinerView v) {
        this.view = v;
    }


    public void startNewGame() {
        int[] gameSettings = getGameSettings();

        model.startNewGame(gameSettings[0], gameSettings[1], gameSettings[2]);

        //view.createBoard(model);
        view.createBoard();
    }

    /**
     * Method returns an array with Settings of the Game
     * @return an array of int which has length = 3,
     * this array is representing game settings
     * index 0 - is mine field height
     * index 1 - is mine field width
     * index 2 - is mines amount
     */
    protected abstract int[] getGameSettings();

    public void LeftMouseClick(int row, int col) {
        Cell cell = model.getCell(row, col);
        if (cell.getStatus() == CellStatus.Opened ||
            cell.getStatus() == CellStatus.Flagged ||
            model.isGameOver() )
            return;
        else {
            model.openCell(row, col);
            view.updateView();
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
            model.nextCellMark(row, col);
            view.updateView();

            if (model.isWin())
                view.showWinMessage();
        }
    }
}
