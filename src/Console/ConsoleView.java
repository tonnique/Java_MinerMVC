package Console;

import Model.Cell;
import Model.MinerModel;
import View.IMinerView;

/**
 * Created by Antony on 29.09.2016.
 */
public class ConsoleView implements IMinerView {
    MinerModel model;
    ConsoleController controller;

    //String[][] cells;

    //int field_height;
    //int field_width;


    public ConsoleView(MinerModel m, ConsoleController c) {
        model = m;
        controller = c;
        controller.setView(this);
        controller.startGame();

    }

    @Override
    //public void createBoard(MinerModel m) {
    public void createBoard() {
        updateView();
    }

    @Override
    public void showWinMessage() {
        System.out.println("Вы победили!");
    }

    @Override
    public void showGameOverMessage() {
        System.out.println("Вы проиграли!");
    }

    @Override
    public void updateView() {
        System.out.println(model.getBombsNumber() + " Mines lefts");

        int field_height = model.getHeight();
        int field_width = model.getWidth();

        for (int row = 0; row < field_height; row++) {
            for (int col = 0; col < field_width; col++) {
                Cell cell = model.getCell(row, col);

                switch (cell.getStatus()) {
                    case Closed :
                        System.out.print("|x");
                        break;
                    case Flagged:
                        System.out.print("|F");
                        break;
                    case Questioned:
                        System.out.print("|?");
                        break;
                    case Opened:
                        if (!cell.hasBomb()) {
                            int cellValue = cell.getValue();
                            if (cellValue == 0) {
                                System.out.print("| ");
                            }
                            else {
                                System.out.print("|" + cellValue);
                            }
                        }
                        else {
                            System.out.print("|*");
                        }
                        break;
                    default:
                        return;
                }
            }
            System.out.println("|");
        }
        System.out.println();

    }
}
