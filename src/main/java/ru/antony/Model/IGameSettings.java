package ru.antony.Model;

/**
 * Created by Antony on 23.09.2016.
 */
public interface IGameSettings {

    // levels for the game
    enum GameLevels {
        Beginner, Medium, Expert, Custom
    };

    // States of the cell
    enum CellStatus {
        Opened, Closed, Flagged, Questioned
    }

    enum GameState {WIN, LOOSE, RUNNING}

    int MIN_FIELD_HEIGHT = 2;
    int MIN_FIELD_WIDTH = 2;

    int MAX_FIELD_HEIGHT = 37;
    int MAX_FIELD_WIDTH = 76;

    // Максимальное количество бомб не должно превышать HEIGHT * WIDTH * MAX_BOMB_RATIO
    double MAX_BOMB_RATIO = 0.25;

    int BEGINNER_MINEFIELD_HEIGHT = 10;
    int BEGINNER_MINEFIELD_WIDTH = 10;
    int BEGINNER_NUM_OF_BOMBS = 10;

    int MEDIUM_MINEFIELD_HEIGHT = 16;
    int MEDIUM_MINEFIELD_WIDTH = 16;
    int MEDIUM_NUM_OF_BOMBS = 40;


    int EXPERT_MINEFIELD_HEIGHT = 16;
    int EXPERT_MINEFIELD_WIDTH = 30;
    int EXPERT_NUM_OF_BOMBS = 99;

    int CUSTOM_MINEFIELD_HEIGHT = 20;
    int CUSTOM_MINEFIELD_WIDTH = 30;
    int CUSTOM_NUM_OF_BOMBS = 145;

}
