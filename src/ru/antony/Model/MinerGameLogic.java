package ru.antony.Model;

import java.util.ArrayList;
import java.util.Random;

import ru.antony.Model.IGameSettings.*;

/**
 * Created by Antony on 21.09.2016.
 */
public class MinerGameLogic implements IMinerModel {

    private IMinerCell[][] cells;

    private boolean firstStep; // был произведен первый ход?
    private boolean gameOver;

    private int field_height;
    private int field_width;
    private int bomb_amount;
    private int flags_amount;

    /** Конструктор по умолчанию. Здесь создается минное поле по умолчанию для начинающего игрока (Beginner) */
    public MinerGameLogic() {
        this.field_height = IGameSettings.BEGINNER_MINEFIELD_HEIGHT;
        this.field_width = IGameSettings.BEGINNER_MINEFIELD_WIDTH;
        this.bomb_amount = IGameSettings.BEGINNER_NUM_OF_BOMBS;
        flags_amount = bomb_amount;
        gameOver = true;
    }

    /**
     * Здесь создается и заполняется массив ячеек (Cell[][]) минного поля новыми ячейками.
     * Они заполняются пустыми (по-умолчанию) значениями, определенными в классе Cell.
     * Мины здесь не устанавливаются */
    public void startNewGame(int height, int width, int b ) {

        firstStep = true;
        gameOver = false;

        setGameValues(height, width, b);

        cells = new IMinerCell[field_height][field_width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    // checking correct values and sets them
    private void setGameValues(int h, int w, int b) {
        if (h >= IGameSettings.MIN_FIELD_HEIGHT && h <= IGameSettings.MAX_FIELD_HEIGHT){
            this.field_height = h;
        }
        else {
            this.field_height = (this.field_height == 0) ? 10 : field_height ;
        }

        if (w >= IGameSettings.MIN_FIELD_WIDTH && w <= IGameSettings.MAX_FIELD_WIDTH) {
            this.field_width = w;
        }
        else {
            this.field_height = (this.field_width == 0) ? 10 : field_width;
        }


        if ((field_height * field_width * IGameSettings.MAX_BOMB_RATIO > b))
        {
            this.bomb_amount = b;
        }
        else {
            this.bomb_amount = (int)(field_height * field_width * IGameSettings.MAX_BOMB_RATIO);
        }

        flags_amount = bomb_amount;
    }

    // Возвращает ячейку в массиве с указанными индексами
    private IMinerCell getCell(int row, int col) {
        if (row < 0 || col < 0 || this.field_height <= row || this.field_width <= col) {
            return null;
        }
        else {
            return cells[row][col];
        }
    }

    /**
     * В этом методе минное поле заполняется минами.
     * Я решил вызывать этот метод не сразу из конструктора,
     * а после выбора ячейки пользователем, т.к. я хочу дать возможность,
     * пользователю не проигрывать с 1го хода.
     */
    private void setMines() {

        Random rand = new Random();

        while(bomb_amount > 0) {
            int row = rand.nextInt(field_height);
            int col =  rand.nextInt(field_width);

            /**
             * Здесь проверяется, что ячейка, которую определил рандом, еще не имеет мины
             * А так же проверяется, что эта ячейка не первая кот. открывает пользователь
             */
            if (!cells[row][col].hasBomb() && (cells[row][col].getStatus() != CellStatus.Opened)) {
                cells[row][col].setBomb(true);

                bomb_amount--;
            }
        }
    }


    /**
     * Этот метод возвращает массив соседних ячейкек для текущей ячейки с координатами (row, col)
     * Этот метод вызывается из метода openCell, в случае, если у ячейки не будет рядом мин (она пустая).
     * Так же этом метод вызывается в методе countMinesAroundCell(Cell cell) -
     * для подсчета кол-ва мин рядом с открытой клеткой
     *
     * @param cell - ячейка для которой определяются соседние клетки
     * @return - возвращает массив ячеек, которые примыкают к текущей
     */
    private IMinerCell[] getCellNeighbours(IMinerCell cell) {

        int row = cell.getRow();
        int col = cell.getCol();

        ArrayList<IMinerCell> neighbours = new ArrayList<>();
        IMinerCell tmpCell;
        for (int r = row - 1; r <= row+1; r++) {

            tmpCell = getCell(r, col-1);
            if (tmpCell != null) neighbours.add(tmpCell);

            if (r != row) {
                tmpCell = getCell(r, col);
                if (tmpCell != null) neighbours.add(tmpCell);
            }

            tmpCell = getCell(r, col+1 );
            if (tmpCell != null) neighbours.add(tmpCell);
        }

        IMinerCell[] cells = new IMinerCell[1];
        return neighbours.toArray(cells);
    }

    /**
     * Этот метод подсчитывает кол-во мин вокруг ячейки */
    private int countMinesAroundCell(IMinerCell cell) {
        IMinerCell[] neighbours = getCellNeighbours(cell);
        int sum = 0;
        for (IMinerCell c : neighbours) {
            if (c.hasBomb()) {
                sum++;
            }
        }
        return sum;
    }

    private void openAllMines() {
        for (int row = 0; row < field_height; row++)
            for (int col = 0; col < field_width; col++) {
                IMinerCell cell = getCell(row, col);
                if (cell.hasBomb() && cell.getStatus() != CellStatus.Flagged) {
                    cell.open();
                }
            }
    }

    /**
     * Открывает клетку. Если это первая открытая клетка - тогда устанавливаются мины
     * во всем игровом поле
     * @param row - коодината открываемой клетки по вертикали (ряды)
     * @param col - коодината открываемой клетки по горизонтали (колонки)
     */
    public void openCell(int row, int col) {
        IMinerCell cell = getCell(row, col);
        if (cell == null) return;
        if (cell.getStatus() == CellStatus.Flagged) return;

        this.openCell(cell);
    }

    /**
     * Открывает клетку. Если это первая открытая клетка - тогда устанавливаются мины
     * во всем игровом поле
     * @param cell - ячейка, которая открывается
     */
    private void openCell(IMinerCell cell) {
        if (cell == null) return;
        if (cell.getStatus() == CellStatus.Flagged) return;

        cell.open();

        if (cell.hasBomb()) {
            openAllMines();
            gameOver = true;
            return;
        }

        /** минное поле создается только после первого хода */
        if (firstStep == true) {
            firstStep = false;
            setMines();
        }

        int cellValue = countMinesAroundCell(cell);
        cell.setValue(cellValue);

        if (cellValue == 0) {
            IMinerCell[] neighbours = getCellNeighbours(cell);
            for (IMinerCell с : neighbours) {
                if (с.getStatus() != CellStatus.Opened && с.getStatus() != CellStatus.Flagged) {
                    this.openCell(с);
                }
            }
        }
    }

    public void nextCellMark(int row, int col) {
        IMinerCell cell = getCell(row, col);
        if (cell == null) return;

        cell.setNextState();
        if (cell.getStatus() == CellStatus.Flagged) {
            flags_amount--;
        }
        else if (cell.getStatus() == CellStatus.Questioned) {
            flags_amount++;
        }
    }

    public CellStatus getCellStatus(int row, int col) throws IllegalArgumentException {
        IMinerCell cell = getCell(row, col);
        if (cell == null)
            throw new IllegalArgumentException(
                    "Ошибка. Для ячейки были указаны не верные координаты, или ячейка ещё не создалась."
            );

        return cell.getStatus();
    }

    public int getCellValue(int row, int col) throws IllegalArgumentException {
        IMinerCell cell = getCell(row, col);
        if (cell == null)
            throw new IllegalArgumentException(
                "Ошибка. Для ячейки были указаны не верные координаты, или ячейка ещё не создалась."
            );

        return cell.getValue();}

    public boolean isCellHasBomb(int row, int col) throws IllegalArgumentException {

        IMinerCell cell = cells[row][col];
        if (cell == null)
            throw new IllegalArgumentException(
              "Ошибка. Для ячейки были указаны не верные координаты, или ячейка ещё не создалась."
            );

        return cells[row][col].hasBomb();
    }

    /** Проверка, что пользователь выиграл игру
     *  The win comes in conditions:
     *  the user marked all mines with flags - there are no flags left
     *  and all ordinary (not mined) cells are opened
     *
     * @return returns true value if all wining game conditions  are met or false value in opposite case.
     */
    public boolean isWin() {

        if (flags_amount != 0) return false;

        for (int row = 0; row < this.field_height; row++) {
            for (int col = 0; col < this.field_width; col++) {
                IMinerCell cell = cells[row][col];

                // Every ordinary (not mined) cells must be opened
                if (!cell.hasBomb() && cell.getStatus() != CellStatus.Opened ) {
                    return false;
                }
            }
        }

        gameOver = true; //This not means that the user has failed the game -
                         // this means exactly - the game is finished
        return true;
    }

    public boolean isGameOver() { return gameOver; }

    public int getHeight() { return field_height; }

    public int getWidth() { return field_width; }

    /* Этот метод возвращает кол-во флажков.
       Раньше я хотел, сообщать кол-во мин, но, поскольку при установке флажка,
       у нас в игровом окне меняется информация о кол-ве мин, - что не является правдой -
       т.к. у нас кол-во мин остается одним и тем же, а меняются кол-во флажков
     */
    public int getBombsNumber() { return flags_amount; }

    // this is only for debugging purpose
    private void displayMines() {

        boolean yes = false;
        for (int row = 0; row < field_height; row++) {
            for (int col = 0; col < field_width; col++) {
                if (cells[row][col].hasBomb()) {
                    yes = true;
                    System.out.print("* " + (row + 1) + ", " + (col + 1) + " | ");
                }
            }
            if (yes) System.out.println();
            yes = false;
        }
        System.out.println();

    }
}
