package View;

import Model.IGameSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antony on 23.09.2016.
 */
public class SettingsDialog extends JDialog implements ActionListener {
//public class SettingsDialog extends JFrame implements ActionListener {

    private int FRAME_WIDTH = 400;
    private int FRAME_HEIGHT = 300;

    private int gridx = 0;
    private int gridy = 0;

    // нажал ли пользователь ОК при работе с окном
    private boolean userConfirmed = false;

    private int minefield_height;
    private int minefield_width;
    private int bomb_number;

    JPanel settingsPanel = new JPanel(new GridBagLayout());
    JButton btnOK;
    JButton btnCancel;
    ButtonGroup group = new ButtonGroup();

    public SettingsDialog(JFrame owner) {
        super(owner, "Настройки игры", true);

        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        x = (x - FRAME_WIDTH) / 2 ;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        y = (y - FRAME_HEIGHT) / 2 ;

        setBounds(x, y, FRAME_WIDTH, FRAME_HEIGHT);

        setControls();

        pack();
    }




    private void setControls() {

        btnOK = new JButton("OK");
        btnOK.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        fillSettingsPanel();

        // Нижняя панель с кнопками
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnCancel);

        settingsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(settingsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void fillSettingsPanel() {

        gridx = 1; gridy = 0;

        settingsPanel.add(new JLabel("Высота"), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(3,3,3,3), 0, 0));

        settingsPanel.add(new JLabel("Ширина"), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(3,3,3,3), 0, 0));

        settingsPanel.add(new JLabel("Мины"), new GridBagConstraints(gridx++, gridy++, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(3,3,3,3), 0, 0));


        addLevelSettings("Новичок", IGameSettings.BEGINNER_MINEFIELD_HEIGHT,
                IGameSettings.BEGINNER_MINEFIELD_WIDTH, IGameSettings.BEGINNER_NUM_OF_BOMBS, true);

        addLevelSettings("Бывалый", IGameSettings.MEDIUM_MINEFIELD_HEIGHT,
                IGameSettings.MEDIUM_MINEFIELD_WIDTH, IGameSettings.MEDIUM_NUM_OF_BOMBS, false);

        addLevelSettings("Эксперт", IGameSettings.EXPERT_MINEFIELD_HEIGHT,
                IGameSettings.EXPERT_MINEFIELD_WIDTH, IGameSettings.EXPERT_NUM_OF_BOMBS, false);

        addLevelSettings("Custom", IGameSettings.CUSTOM_MINEFIELD_HEIGHT,
                IGameSettings.CUSTOM_MINEFIELD_WIDTH, IGameSettings.CUSTOM_NUM_OF_BOMBS, false);

    }

    private void addLevelSettings(String name, int h, int w, int b, boolean select) {

        if (gridx > 3) gridx = 0;

        JRadioButton rb = new JRadioButton("", select);
        rb.addActionListener(this);
        group.add(rb);

        if (name == "Custom") {

            rb.setText("Особый");


            settingsPanel.add(rb, new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JTextField(h + ""), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JTextField(w + ""), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JTextField(b + ""), new GridBagConstraints(gridx++, gridy++, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0));
        }
        else {

            rb.setText(name);

            settingsPanel.add(rb, new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JLabel(h + ""), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JLabel(w + ""), new GridBagConstraints(gridx++, gridy, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0));

            settingsPanel.add(new JLabel(b + ""), new GridBagConstraints(gridx++, gridy++, 1, 1, 0, 0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0));
        }
    }

    public int getFieldWidth() { return minefield_width; }

    public int getFieldHeight() { return minefield_height; }

    public int getBombsNumber() { return bomb_number;  }

    public boolean isUserConfirmed() { return  userConfirmed; }


    // метод заглушка
    private void setLevel(String text) {

        switch (text) {
            case "Новичок":
                minefield_height = IGameSettings.BEGINNER_MINEFIELD_HEIGHT;
                minefield_width = IGameSettings.BEGINNER_MINEFIELD_WIDTH;
                bomb_number = IGameSettings.BEGINNER_NUM_OF_BOMBS;
                break;
            case "Бывалый":
                minefield_height = IGameSettings.MEDIUM_MINEFIELD_HEIGHT;
                minefield_width = IGameSettings.MEDIUM_MINEFIELD_WIDTH;
                bomb_number = IGameSettings.MEDIUM_NUM_OF_BOMBS;
                break;
            case "Эксперт":
                minefield_height = IGameSettings.EXPERT_MINEFIELD_HEIGHT;
                minefield_width = IGameSettings.EXPERT_MINEFIELD_WIDTH;
                bomb_number = IGameSettings.EXPERT_NUM_OF_BOMBS;
                break;
            case "Особый":
                minefield_height = IGameSettings.CUSTOM_MINEFIELD_HEIGHT;
                minefield_width = IGameSettings.CUSTOM_MINEFIELD_WIDTH;
                bomb_number = IGameSettings.CUSTOM_NUM_OF_BOMBS;
                break;

            default:
                minefield_height = IGameSettings.BEGINNER_MINEFIELD_HEIGHT;
                minefield_width = IGameSettings.BEGINNER_MINEFIELD_WIDTH;
                bomb_number = IGameSettings.BEGINNER_NUM_OF_BOMBS;
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JRadioButton) {
            AbstractButton button = (AbstractButton) e.getSource();

            setLevel(button.getText());

        }

        else if (e.getSource() instanceof JButton) {

            JButton srcButton = (JButton) e.getSource();
            if (srcButton == btnOK) {
                userConfirmed = true;
            } else {
                userConfirmed = false;
            }

            this.setVisible(false);
        }

    }


}
