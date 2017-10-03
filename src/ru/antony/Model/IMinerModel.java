package ru.antony.Model;

public interface IMinerModel {

    void startNewGame(int height, int width, int b );

    void openCell(int row, int col);

    IGameSettings.CellStatus getCellStatus(int row, int col) throws IllegalArgumentException;

    boolean isCellHasBomb(int row, int col) throws IllegalArgumentException;

    int getCellValue(int row, int col) throws IllegalArgumentException;

    void nextCellMark(int row, int col);

    boolean isGameOver();

    boolean isWin();

    int getBombsNumber();

    int getHeight();

    int getWidth();
}
