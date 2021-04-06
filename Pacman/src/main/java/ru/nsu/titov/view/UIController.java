package ru.nsu.titov.view;

import ru.nsu.titov.controller.AppState;
import ru.nsu.titov.controller.MainController;
import ru.nsu.titov.model.ModelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIController {

    private CardLayout layout;

    private JFrame mainWindow;

    private JPanel mainPanel;
    private GamePanel gamePanel;
    private SettingsPanel settingsPanel;
    private LeaderboardPanel leaderboardPanel;

    private JButton mainMenuStartButton;
    private JButton mainMenuExitButton;
    private JButton mainMenuSettingsButton;
    private JButton mainMenuLeaderboardButton;

    private MyPainter painter = new MyPainter();

    public UIController(MainController controller) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
//            e.printStackTrace();
//        }

        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(new Dimension(UISettings.WINDOW_WIDTH, UISettings.WINDOW_HEIGHT));
        mainWindow.setResizable(false);

        layout = new CardLayout();

        mainPanel = new JPanel();

        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.YELLOW);

        settingsPanel = new SettingsPanel();
        settingsPanel.setBackground(Color.BLUE);

        leaderboardPanel = new LeaderboardPanel();
        leaderboardPanel.setBackground(Color.CYAN);

        mainMenuStartButton = new JButton("Start");
        mainMenuStartButton.setLocation(20, 180);
        mainMenuStartButton.setSize(new Dimension(UISettings.BUTTON_WIDTH, UISettings.BUTTON_HEIGHT));
        mainMenuStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMode(AppState.GAME);
            }
        });

        mainMenuSettingsButton = new JButton("Settings");
        mainMenuSettingsButton.setLocation(20, 290);
        mainMenuSettingsButton.setSize(new Dimension(UISettings.BUTTON_WIDTH, UISettings.BUTTON_HEIGHT));
        mainMenuSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMode(AppState.SETTINGS);
            }
        });

        mainMenuLeaderboardButton = new JButton("Leaderboard");
        mainMenuLeaderboardButton.setLocation(20, 400);
        mainMenuLeaderboardButton.setSize(new Dimension(UISettings.BUTTON_WIDTH, UISettings.BUTTON_HEIGHT));
        mainMenuLeaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMode(AppState.LEADERBOARD);
            }
        });

        mainMenuExitButton = new JButton("Exit");
        mainMenuExitButton.setLocation(20, 510);
        mainMenuExitButton.setSize(new Dimension(UISettings.BUTTON_WIDTH, UISettings.BUTTON_HEIGHT));
        mainMenuExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exit();
            }
        });

        mainPanel.setLayout(layout);

        mainPanel.add(gamePanel, AppState.GAME.toString());
        mainPanel.add(settingsPanel, AppState.SETTINGS.toString());
        mainPanel.add(leaderboardPanel, AppState.LEADERBOARD.toString());

        layout.show(mainPanel, "game");

        mainWindow.add(mainMenuStartButton);
        mainWindow.add(mainMenuSettingsButton);
        mainWindow.add(mainMenuLeaderboardButton);
        mainWindow.add(mainMenuExitButton);

        mainWindow.add(mainPanel);

        mainWindow.setVisible(true);
    }

    public void setPanel(String panelName) {
        layout.show(mainPanel, panelName);
        mainWindow.repaint();
    }

    public void repaintGameField(ModelController model, int modelTicksPassed) {
        painter.setGraphics(gamePanel.getGraphics());
        painter.setTicksPassed(modelTicksPassed);
        model.repaint(painter);
    }
}
