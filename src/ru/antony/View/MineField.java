package ru.antony.View;

import ru.antony.Model.IGameSettings;
import ru.antony.Model.IMinerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

import static ru.antony.View.IMinerView.COLOR_UNCHECKED;


public class MineField extends JPanel {

    private JLabel[][] cells;

    private int height; // the height of the minefield (value gets from a model)
    private int width; // the width of the minefield (value gets from a model)
    private IMinerModel model;
    // внутренняя панель, на которой создается игровое поле
    private JPanel mineFieldPanel = new JPanel();

    public MineField(IMinerModel model) {
        this.model = model;
        height = model.getHeight();
        width = model.getWidth();
        cells = new JLabel[height][width];

        mineFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        createField(height, width);
        mineFieldPanel.setPreferredSize(new Dimension(width * IMinerView.cellSize, height * IMinerView.cellSize));
        this.setBorder(new EmptyBorder(3,3,3,3));
    }

    private void createField(int h, int w) {
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                cells[x][y] = new JLabel();
                //cells[x][y].setBorder(new LineBorder(Color.DARK_GRAY));
                cells[x][y].setBorder(BorderFactory.createEtchedBorder(COLOR_UNCHECKED, Color.DARK_GRAY));
                cells[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                cells[x][y].setOpaque(true);
                cells[x][y].setBackground(COLOR_UNCHECKED);
                cells[x][y].setPreferredSize(new Dimension(IMinerView.cellSize, IMinerView.cellSize));
                mineFieldPanel.add(cells[x][y]);
            }
        }
        this.add(mineFieldPanel);
    }

    public void syncWithModel() {

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                //Cell cell = model.getCell(row, col);

                try {
                    switch (model.getCellStatus(row, col)) {
                        case Closed :
                            cells[row][col].setText(" ");
                            break;
                        case Flagged:
                            //if (model.isGameOver() && !model.isCellHasBomb(row, col)) {
                            if (model.checkGameStatus() == IGameSettings.GameState.LOOSE && !model.isCellHasBomb(row, col)) {
                                cells[row][col].setText("X");
                            } else {
                                cells[row][col].setText("F");
                            }
                            break;
                        case Questioned:
                            cells[row][col].setText("?");
                            break;
                        case Opened:
                            if (!model.isCellHasBomb(row, col)) {
                                cells[row][col].setBackground(IMinerView.colors[0]);

                                int cellValue = model.getCellValue(row, col);
                                String lblText = cellValue + "";
                                switch (cellValue) {
                                    case 0:
                                        lblText = "";
                                        break;
                                    case 1:
                                        cells[row][col].setForeground(IMinerView.colors[1]);
                                        break;
                                    case 2:
                                        cells[row][col].setForeground(IMinerView.colors[2]);
                                        break;
                                    case 3:
                                        cells[row][col].setForeground(IMinerView.colors[3]);
                                        break;
                                    case 4:
                                        cells[row][col].setForeground(IMinerView.colors[4]);
                                        break;
                                    case 5:
                                        cells[row][col].setForeground(IMinerView.colors[5]);
                                        break;
                                    case 6:
                                        cells[row][col].setForeground(IMinerView.colors[6]);
                                        break;
                                    case 7:
                                        cells[row][col].setForeground(IMinerView.colors[7]);
                                        break;
                                    case 8:
                                        cells[row][col].setForeground(IMinerView.colors[8]);
                                        break;
                                }
                                cells[row][col].setText(lblText);
                            }
                            else {
                                cells[row][col].setBackground(IMinerView.COLOR_MINED);
                            }
                            break;
                        default:
                            return;
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                repaint();
            }
        }
    }

    @Override
    public void addMouseListener(MouseListener listener) {
        mineFieldPanel.addMouseListener(listener);
    }

}
