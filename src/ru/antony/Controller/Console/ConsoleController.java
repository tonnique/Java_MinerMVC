package ru.antony.Controller.Console;

import ru.antony.Controller.MinerController;
import ru.antony.Model.Cell;
import ru.antony.Model.IGameSettings.*;
import ru.antony.Model.MinerModel;

import java.util.Scanner;

/**
 * Created by Antony on 29.09.2016.
 */
public class ConsoleController extends MinerController {

    String userInput = "";
    boolean work = true;
    MinerModel model;

    public ConsoleController(MinerModel m) {
        super(m);
        model = m;
    }

    @Override
    public void startNewGame() {

        Scanner in = new Scanner(System.in);

        printUserCommands();

        while (work) {
            System.out.println("Ожидаю комманду. Для справки нажмите H");
            System.out.print(">");

            userInput = in.nextLine();

            switch (userInput.toUpperCase()) {

                case "S":
                    selectCell();
                    break;
                case "N":
                    super.startNewGame();
                case "H":
                    printUserCommands();
                    break;
                case "Q":
                    work = false;
                    break;
                default:
                    System.out.println("Данная команда неизвестна. Для получения справки по командам нажмите H");
            }
        }
    }

    // Выводит пользователю доступные комманды
    private void printUserCommands() {

        System.out.println("Чтобы выбрать ячейку нажмите S");
        System.out.println("Чтобы начать новую игру нажмите N");
        //System.out.println("Вывести эту справку снова нажмите H");
        System.out.println("Для выхода нажмите Q");
    }

    private void selectCell() {
        Scanner in = new Scanner(System.in);

        while(!model.isGameOver()) {

            System.out.println("Пожалуйста, укажите координаты ячейки (x, y). Для отмены нажмите Q");
            int row = 0;
            int col = 0;

            try {
                System.out.print("Ряд Y=");
                userInput = in.nextLine();
                if(userInput.toUpperCase().equals("Q")) {break;}
                row = Integer.valueOf(userInput);

                System.out.print("Колонка X=");
                userInput = in.nextLine();
                if(userInput.toUpperCase().equals("Q")) {break;}
                col = Integer.valueOf(userInput);

            } catch (NumberFormatException ex) {
                System.out.println("Были указаны не верные данные. Пожалуйста попробуйте снова.");
                break;
            }

            //boolean w = true;
            while (true) {
                System.out.println("Что вы хотие сделать с выбранной ячейкой {" + row + "," + col + "} ? " +
                        "Открыть {O} или отметить {R}. Дня отмены нажмите С");
                userInput = in.nextLine();

                if (userInput.toUpperCase().equals("C")) {
                    //w = false;
                    break;
                } else if (userInput.toUpperCase().equals("O")) {
                    //w = false;
                    super.LeftMouseClick(row, col);
                    break;
                } else if (userInput.toUpperCase().equals("R")) {
                    //w = false;
                    super.RightMouseClick(row, col);
                    break;
                }
            }
        }
    }

    @Override
    protected int[] getGameSettings() {
        return  new int[] {10, 10, 10};
    };

    @Override
    public void RightMouseClick(int row, int col) {
        Cell cell = model.getCell(row, col);

        if (cell.getStatus() == CellStatus.Opened || model.isGameOver()) view.updateMineField();
        else {
            model.nextCellMark(cell);
            view.updateMineField();

            if (model.isWin())
                view.showWinMessage();
        }
    }
}
