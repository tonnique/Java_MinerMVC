package ru.antony.View.Console;

import ru.antony.Controller.IMinerController;
import ru.antony.Model.IMinerModel;
import ru.antony.View.IMinerView;

/**
 * Created by Antony on 29.09.2016.
 */
public class ConsoleView implements IMinerView {
    IMinerModel model;
    IMinerController controller;

    public ConsoleView(IMinerModel m, IMinerController c) {
        model = m;
        controller = c;
        controller.setView(this);
        controller.startNewGame();
    }

    //@Override
    public void addNewMineField() {
        updateMineField();
    }

    //@Override
    public void showWinMessage() {
        System.out.println("Вы победили!");
    }

    //@Override
    public void showGameOverMessage() {
        System.out.println("Вы проиграли!");
    }

    //@Override
    public void updateMineField() {
        System.out.println(model.getBombsNumber() + " Mines lefts");

        int field_height = model.getHeight();
        int field_width = model.getWidth();

        for (int row = 0; row < field_height; row++) {
            for (int col = 0; col < field_width; col++) {

                try {
                    switch (model.getCellStatus(row, col)) {
                        case Closed:
                            System.out.print("|x");
                            break;
                        case Flagged:
                            System.out.print("|F");
                            break;
                        case Questioned:
                            System.out.print("|?");
                            break;
                        case Opened:
                            if (!model.isCellHasBomb(row, col)) {
                                int cellValue = model.getCellValue(row, col);

                                if (cellValue == 0) {
                                    System.out.print("| ");
                                } else {
                                    System.out.print("|" + cellValue);
                                }
                            } else {
                                System.out.print("|*");
                            }
                            break;
                        default:
                            return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }
}
