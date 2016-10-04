package Model;



public class Cell {

    private int row;
    private int col;
    private boolean hasBomb;
    private CellStatus status; // это поле хранит состояние поля - открыто-закрыто-флаг-вопрос
    private int value; // это поле хранит значение рядом стоящих мин

    public Cell(int r, int c) {
        this.row = r;
        this.col = c;
        status = CellStatus.Closed;
        hasBomb = false;
        value = 0;
    }


    public void setBomb(boolean b) {
        //this.value = (bomb == true) ? ICell.bombValue : 0;
        hasBomb = b;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setValue(int val) { this.value = val; }

    // возвращает значение соотвествующее количеству соседних клеток с минами
    public int getValue() { return value; }

    // Метод при нажатии правой кнопки мыши устанавливает "следующее" состояние
    public void nextState() {
        String[] statArray =  {"Closed","Flagged","Questioned"};

        int id = 0;
        for (int i = 0; i < statArray.length; i++) {
            if (status.toString() == statArray[i]) {
                id = i; break;
            }
        }

        status = CellStatus.valueOf(statArray[(id + 1) % statArray.length]);
    }

    // Метод возвращает состояние ячейки
    public CellStatus getStatus() { return status; }

    // устанавливает статус ячейки на открытая (Opened)
    public void open() {
        if (this.status != CellStatus.Flagged)
            status = CellStatus.Opened;
    }

    public int getRow() { return  row; }

    public int getCol() { return  col; }

}
