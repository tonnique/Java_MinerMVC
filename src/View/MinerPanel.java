package View;


import Model.Cell;
import Model.MinerModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;



public class MinerPanel extends JPanel {

    private JLabel[][] cells;

    public static final int labelHeight = 25;
    public static final int labelWidth = 25;

    private final Color COLOR_UNCKECKED = new Color(181, 230, 29);
    private final Color COLOR_OPENED = new Color(231, 235, 165);
    private final Color COLOR_MINED = new Color(255, 0, 0);

    private final Color COLOR1 = Color.BLUE;
    private final Color COLOR2 = new Color(0, 100, 0);
    private final Color COLOR3 = Color.RED;
    private final Color COLOR4 = new Color(0, 0, 150);
    private final Color COLOR5 = new Color(170, 0, 20);
    private final Color COLOR6 = new Color(0, 150, 170);
    private final Color COLOR7 = Color.BLACK;
    private final Color COLOR8 = Color.GRAY;


    private int height; // the height of the minefield (a model)
    private int width; // the width of the minefield (a model)
    private MinerModel model;

    public MinerPanel(MinerModel model) {
        this.model = model;
        height = model.getHeight();
        width = model.getWidth();
        cells = new JLabel[height][width];

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        createField(height, width);
        setPreferredSize(new Dimension(width * labelWidth, height * labelHeight));
    }

    private void createField(int h, int w) {
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                cells[x][y] = new JLabel();
                cells[x][y].setBorder(new LineBorder(Color.BLACK));
                //cells[x][y].setBorder(new EtchedBorder());
                cells[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                cells[x][y].setOpaque(true);
                cells[x][y].setBackground(COLOR_UNCKECKED);
                cells[x][y].setPreferredSize(new Dimension(labelWidth, labelHeight));
                add(cells[x][y]);
            }
        }
    }

    public void syncWithModel() {

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                Cell cell = model.getCell(row, col);

                switch (cell.getStatus()) {
                    case Closed :
                        cells[row][col].setText(" ");
                        break;
                    case Flagged:
                        cells[row][col].setText("F");
                        break;
                    case Questioned:
                        cells[row][col].setText("?");
                        break;
                    case Opened:
                        if (!cell.hasBomb()) {
                            cells[row][col].setBackground(COLOR_OPENED);

                            int cellValue = cell.getValue();
                            String lblText = cellValue + "";
                            switch (cellValue) {
                                case 0:
                                    lblText = "";
                                    break;
                                case 1:
                                    cells[row][col].setForeground(COLOR1);
                                    break;
                                case 2:
                                    cells[row][col].setForeground(COLOR2);
                                    break;
                                case 3:
                                    cells[row][col].setForeground(COLOR3);
                                    break;
                                case 4:
                                    cells[row][col].setForeground(COLOR4);
                                    break;
                                case 5:
                                    cells[row][col].setForeground(COLOR5);
                                    break;
                                case 6:
                                    cells[row][col].setForeground(COLOR6);
                                    break;
                                case 7:
                                    cells[row][col].setForeground(COLOR7);
                                    break;
                                case 8:
                                    cells[row][col].setForeground(COLOR8);
                                    break;
                            }

                            cells[row][col].setText(lblText);
                        }
                        else {
                            cells[row][col].setBackground(COLOR_MINED);
                        }
                        break;
                    default:
                        return;
                }
                repaint();
            }
        }

    }

}
