package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Antony on 21.09.2016.
 */
public class MinerModel {

    private Cell[][] cells;

    private boolean firstStep; // был произведен первый ход

    private boolean gameOver;

    private int field_height;
    private int field_width;
    private int bomb_amount;

    /** Конструктор по умолчанию. Здесь создается минное поле для начинающего игрока (Beginner)
     * и включен режим безопасного первого хода */
    public MinerModel() {

        this.field_height = IGameSettings.BEGINNER_MINEFIELD_HEIGHT;
        this.field_width = IGameSettings.BEGINNER_MINEFIELD_WIDTH;
        this.bomb_amount = 10;
    }

    /**
     * Здесь создается и заполняется массив ячеек (Cell[][]) минного поля новыми ячейками.
     * Они заполняются пустыми (по-умолчанию) значениями, определенными в классе Cell.
     * Мины здесь не устанавливаются */
    public void startNewGame(int height, int width, int b ) {

        firstStep = true;
        gameOver = false;

        setGameValues(height, width, b);

        cells = new Cell[field_height][field_width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

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


        if ((field_height * field_width * IGameSettings.MAX_BOMB_RATIO
                > field_height * field_width / b))
        {
            this.bomb_amount = b;
        }
        else {
            this.bomb_amount = (int)(field_height * field_width * IGameSettings.MAX_BOMB_RATIO);
        }
    }

    // Возвращает ячейку в массиве с указанными индексами
    public Cell getCell(int row, int col) {
        if (row < 0 || col < 0 || this.field_height <= row || this.field_width <= col) {
            return null;
        }
        else {
            return cells[row][col];
        }
    }


    /**
     * В этом методе минное поле заполняется минами.
     * Я решил вызывать этом метод не сразу из конструктора MinerModel,
     * а где-нибудь после выбора ячейки пользователем, т.к. я хочу
     * сделать возможность, по выбору, не заполянять миной первую ячейку,
     * которую выбирает пользователь, , следовательно - пользователь
     * не будет проигрывать с 1го хода.
     *
     * @param bombNumber - количество мин в поле
     */
    public void setMines(int bombNumber) {

        Random rand = new Random();

        do {
            int row = rand.nextInt(field_height);
            int col =  rand.nextInt(field_width);

            /**
             * Здесь проверяется, что ячейка, которую определил рандом, еще не имеет мины
             * А так же проверяется, что эта ячейка не первая кот. открывает пользователь
             * (можно будет включить такой режим)
             */
            if (!cells[row][col].hasBomb() && (cells[row][col].getStatus() != CellStatus.Opened)) {
                cells[row][col].setBomb(true);

                bombNumber--;
            }
        }
        while(bombNumber > 0);

        //displayMines(); // for debugging only

    }

    /**
     * В методе устанавливаются значения для ячеек минного поля,
     * которые примыкают к полям с миной. Значения определяются для всего поля.
     * Метод вызывается сразу после установки мин */
    private void setNeighborCellsValues() {

        for (int row = 0; row < field_height; row++) {

            for (int col = 0; col < field_width; col++) {

                if (cells[row][col].hasBomb()) {

                    //surrounding cells with numbers
                    if (row != 0 && col != 0)
                        cells[row - 1][col - 1].setValue(cells[row - 1][col - 1].getValue() + 1);

                    if (row != 0) cells[row - 1][col].setValue(cells[row - 1][col].getValue() + 1);

                    if (row != 0 && col != field_width - 1)
                        cells[row - 1][col + 1].setValue(cells[row - 1][col + 1].getValue() + 1);

                    if (col != 0) cells[row][col - 1].setValue(cells[row][col - 1].getValue() + 1);

                    if (col != field_width - 1)
                        cells[row][col + 1].setValue(cells[row][col + 1].getValue() + 1);

                    if (row != field_height - 1 && col != 0)
                        cells[row + 1][col - 1].setValue(cells[row + 1][col - 1].getValue() + 1);

                    if (row != field_height - 1)
                        cells[row + 1][col].setValue(cells[row + 1][col].getValue() + 1);

                    if (row != field_height - 1 && col != field_width - 1)
                        cells[row + 1][col + 1].setValue(cells[row + 1][col + 1].getValue() + 1);
                }
            }
        }
    }

    /**
     * Этот метод вызывается из метода openCell, в случае, если у ячейки не будет рядом мин (она пустая).
     * Этот метод возвращает соседние ячейки для текущей ячейки с координатами (row, col)
     * @param row - координата ячейки в массиве по рядам
     * @param col - координата ячейки в массиве по колонкам
     * @return - возвращает массив ячеек, которые примыкают к текущей
     */
    private Cell[] getCellNeighbours(int row, int col) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        Cell cell;
        for (int r = row - 1; r <= row+1; r++) {

            cell = getCell(r, col-1);
            if (cell != null) neighbours.add(cell);

            if (r != row) {
                cell = getCell(r, col);
                if (cell != null) neighbours.add(cell);
            }

            cell = getCell(r, col+1 );
            if (cell != null) neighbours.add(cell);
        }

        Cell[] cells = new Cell[1];
        return neighbours.toArray(cells);
    }

    /**
     * Этот метод подсчитывает сумму мин вокруг ячейки */
    private int countMinesAroundCell(int row, int col) {
        Cell[] neighbours = getCellNeighbours(row, col);
        int sum = 0;
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i].hasBomb()) {
                sum++;
            }
        }
        return sum;
    }

    private void openAllMines() {
        for (int row = 0; row < field_height; row++) {
            for (int col = 0; col < field_width; col++) {
                Cell cell = getCell(row, col);
                if (cell.hasBomb() && cell.getStatus() != CellStatus.Flagged) {
                    cell.open();
                }
            }
        }
    }

    public void openCell(int row, int col) {
        Cell cell = getCell(row, col);
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
            setMines(bomb_amount);
        }

        int cellValue = countMinesAroundCell(row, col);
        cell.setValue(cellValue);

        if (cellValue == 0) {
            Cell[] neighbours = getCellNeighbours(row, col);
            for (Cell n : neighbours) {
                if (n.getStatus() != CellStatus.Opened && n.getStatus() != CellStatus.Flagged) {
                    this.openCell(n.getRow(), n.getCol());
                }
            }
        }
    }

    public void nextCellMark(int row, int col) {
        Cell cell = getCell(row, col);
        if (cell != null)
            cell.nextState();
        if (cell.getStatus() == CellStatus.Flagged) {
            bomb_amount--;
        }
        else if (cell.getStatus() == CellStatus.Questioned) {
            bomb_amount++;
        }
    }

    // Проверка, что пользователь выиграл игру
    public boolean isWin() {

        if (bomb_amount != 0) return  false;

        for (int row = 0; row < this.field_height; row++) {
            for (int col = 0; col < this.field_width; col++) {
                Cell cell = cells[row][col];

                // Every ordinary (not mined) cells must be opened
                if (!cell.hasBomb() && (cell.getStatus() != CellStatus.Opened &&
                                      cell.getStatus() != CellStatus.Flagged)) {
                    return false;
                }
            }
        }

        gameOver = true; //This not means that the user has failed the game -
                         // this means exactly - the game is over
        return true;
    }

    public boolean isGameOver() { return gameOver; }

    public int getHeight() { return field_height; }

    public int getWidth() { return field_width; }

    public int getBombsNumber() { return bomb_amount; }


    // this is only for debugging purpouse
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
