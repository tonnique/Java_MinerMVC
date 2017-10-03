package ru.antony.Model;


public interface IMinerCell {

    void setBomb(boolean b);
    boolean hasBomb();

    void open();

    void setValue(int value);
    int getValue();

    void setNextState();
    IGameSettings.CellStatus getStatus();

    int getRow();
    int getCol();
}
