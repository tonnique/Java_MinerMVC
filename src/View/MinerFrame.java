package View;

import Controller.MinerController;
import Controller.SwingMinerController;
import Model.MinerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Created by Antony on 07.09.2016.
 */
public class MinerFrame extends JFrame implements IMinerView {

    private SwingMinerController controller;
    private MinerModel model;

   // private static final int FRAME_WIDTH = 640;
   // private static final int FRAME_HEIGHT = 480;

    Container c;
    private JLabel lblStatus;
    JPanel gamePanel;
    private MinerPanel minerGamePanel;

    private int field_width;
    private int field_height;
    private int bombCounter;

    public MinerFrame(MinerModel model, SwingMinerController controller){

        super("Miner");

        this.model = model;
        this.controller = controller;
        controller.setView(this);

        field_height = model.getHeight();
        field_width = model.getWidth();
        bombCounter = model.getBombsNumber();

        c = this.getContentPane();

        setMenu();
        setControls();

        this.setResizable(false);

        controller.startNewGame();

        setFrameOnCenter();
    }

    // метод устанавливает окно по центру экрана
    private void setFrameOnCenter(){

        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        x = (x - this.getWidth()) / 2 ;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        y = (y - this.getWidth()) / 2 ;

        setBounds(x, y, this.getWidth(), this.getHeight());
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();

           JMenu menuFile = new JMenu("Файл");

           JMenuItem mniStart = new JMenuItem("Новая игра");
           JMenuItem mniExit = new JMenuItem("Выйти");

           menuFile.add(mniStart);
           menuFile.addSeparator();
           menuFile.add(mniExit);

           JMenu menuOptions = new JMenu("Настройки");
               JMenuItem mniSettings = new JMenuItem("Выбрать сложность игры");
                mniSettings.setActionCommand("Settings");
                mniSettings.addActionListener(controller);

           menuOptions.add(mniSettings);

        menuBar.add(menuFile);
        menuBar.add(menuOptions);

        this.setJMenuBar(menuBar);
    }

    private void setControls() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(new EmptyBorder(3,3,3,3));

        JButton btnReset = new JButton("Restart game");
        btnReset.setActionCommand("Restart");
        btnReset.addActionListener(controller);

        lblStatus = new JLabel(bombCounter + " Mines");

        topPanel.add(lblStatus, new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, 1,
                0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(3, 3, 3, 3), 0, 0));

        topPanel.add(btnReset, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        topPanel.add(new JPanel(), new GridBagConstraints(2, 0, GridBagConstraints.REMAINDER,
                1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        gamePanel = new JPanel();
        gamePanel.setBorder(new EmptyBorder(3,3,3,3));

        c.add(topPanel, BorderLayout.NORTH);
        c.add(gamePanel, BorderLayout.CENTER);
    }

    // создает игровое поле на основе данных Модели
    public void createBoard() {
    //public void createBoard(MinerModel model) {
        lblStatus.setText(model.getBombsNumber() + " Mines");
        minerGamePanel = new MinerPanel(model);
        minerGamePanel.addMouseListener(controller);
        gamePanel.setVisible(false);
        gamePanel = new JPanel();
        gamePanel.add(minerGamePanel);
        gamePanel.repaint();
        c.add(gamePanel);
        pack();
        gamePanel.setVisible(true);

        //setFrameOnCenter();

    }

    public void showWinMessage() { JOptionPane.showMessageDialog(this, "Congratulations!"); }

    public void showGameOverMessage() { JOptionPane.showMessageDialog(this, "Boom! You lose." ); }

    /*
    public int[] setGameSettings(int h, int w, int b) {
        field_height = h;
        field_width = w;
        bombCounter = b;
    }

    @Override
    public int[] getGameSettings() {
        return new int[] {field_height, field_width, bombCounter};
        //return new int[] {16, 16, 40};
    }

    */

    public void updateView() {
        lblStatus.setText(model.getBombsNumber() + " Mines");
        minerGamePanel.syncWithModel();
    }

}
