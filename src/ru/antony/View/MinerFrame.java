package ru.antony.View;

import ru.antony.Controller.MinerController;
import ru.antony.Controller.SwingMinerController;
import ru.antony.Model.MinerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Created by Antony on 07.09.2016.
 */
public class MinerFrame extends JFrame implements IMinerView {

    //private SwingMinerController controller;
    private MinerController controller;
    private MinerModel model;

    private JLabel lblStatus = new JLabel();
    private MineField mineField;

    public MinerFrame(MinerModel model, MinerController controller){

        super("Miner");

        this.model = model;
        this.controller = controller;
        controller.setView(this);

        setMenu();
        setControls();

        this.setResizable(false);

        controller.startNewGame();

        setFrameOnCenter();
    }

    // метод устанавливает окно по центру экрана
    private void setFrameOnCenter(){
        setLocationRelativeTo(null);
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();

           JMenu menuFile = new JMenu("Файл");

           JMenuItem mniStart = new JMenuItem("Новая игра");
           mniStart.setActionCommand("newGame");
           mniStart.addActionListener((SwingMinerController) controller);

           JMenuItem mniExit = new JMenuItem("Выйти");
           mniExit.setActionCommand("Exit");
           mniExit.addActionListener((SwingMinerController) controller);

           menuFile.add(mniStart);
           menuFile.addSeparator();
           menuFile.add(mniExit);

           JMenu menuOptions = new JMenu("Настройки");
               JMenuItem mniSettings = new JMenuItem("Выбрать сложность игры");
               mniSettings.setActionCommand("Settings");
               mniSettings.addActionListener((SwingMinerController)controller);

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
        btnReset.addActionListener((SwingMinerController)controller);


        topPanel.add(lblStatus, new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, 1,
                0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(3, 3, 3, 3), 0, 0));

        topPanel.add(btnReset, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        topPanel.add(new JPanel(), new GridBagConstraints(2, 0, GridBagConstraints.REMAINDER,
                1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        add(topPanel, BorderLayout.NORTH);
    }

    // добавить новое игровое поле на основе данных Модели
    public void addNewMineField() {
        lblStatus.setText(model.getBombsNumber() + " Mines");
        if (mineField != null) mineField.setVisible(false);
        mineField = new MineField(model);
        mineField.addMouseListener((SwingMinerController)controller);

        add(mineField, BorderLayout.CENTER);
        pack();

        setFrameOnCenter();
    }

    public void showWinMessage() { JOptionPane.showMessageDialog(this, "Congratulations!"); }

    public void showGameOverMessage() { JOptionPane.showMessageDialog(this, "Boom! You lose." ); }

    public void updateMineField() {
        lblStatus.setText(model.getBombsNumber() + " Mines");
        mineField.syncWithModel();
    }

}
