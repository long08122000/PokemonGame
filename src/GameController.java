import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController extends JPanel {
    private static final int TIME = 100;
    private JProgressBar timeUpProgress = new JProgressBar();
    private JButton rePlay = new JButton("Chơi lại");
    public JLabel scoreLabel = new JLabel();
    public JLabel scoreText = new JLabel();

    public void fixScore(int x) {
        scoreText.setText(String.valueOf(x));
    }

    private Timer timer;
    private GameBoard gameBoard;

    public GameController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        initSetting();
        addButtonReplay();
        addTimeUpProgress();
        addScore();
        setCountDown();
    }

    public void initSetting() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        setPreferredSize(new Dimension(Main.WIDTH - 60, 100));
    }

    private void addButtonReplay() {
        rePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rePlay();
//                gameBoard.removeAll();
//                gameBoard.revalidate();
//                gameBoard.repaint();

                Main.mainPanel.remove(gameBoard);
                gameBoard = new GameBoard();
                Main.mainPanel.add(gameBoard);
            }
        });
        add(rePlay);
    }

    private void addTimeUpProgress() {
        timeUpProgress.setMinimum(0);
        timeUpProgress.setMaximum(TIME);
        timeUpProgress.setValue(TIME);
        timeUpProgress.setPreferredSize(new Dimension(Main.WIDTH - 250, 28));
        add(timeUpProgress);
    }

    private void addScore() {
        String scorelabel = "Score :";
        scoreLabel.setBackground(Color.red);
        scoreLabel.setText(scorelabel);

        String score = gameBoard.getScore() + "";
        scoreText.setText(score);
        add(scoreLabel);
        add(scoreText);
    }

    private void setCountDown() {
        timeUpProgress.setValue(TIME);
        ActionListener listener = new ActionListener() {
            int counter = TIME;

            public void actionPerformed(ActionEvent ae) {
                counter--;
                GameController.this.timeUpProgress.setValue(counter);
                if (counter < 1) {
                    GameController.this.timer.stop();
                    int op = JOptionPane.showConfirmDialog(new JFrame(), "Bạn có muốn chơi lại không ?", "Bạn chơi gà vcl", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        rePlay();
//                        gameBoard.removeAll();
//                        gameBoard.revalidate();
//                        gameBoard.repaint();

//                        Main.mainPanel.remove(gameBoard);
//                        gameBoard = new GameBoard();
//                        Main.mainPanel.add(gameBoard);
                    } else if (op == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
    }

    private void rePlay() {
        timer.stop();
        setCountDown();
        gameBoard.setSCORE(0);
        fixScore(gameBoard.getScore());
    }

    private SetListener gameAction;

    public void addAction(SetListener gameAction) {
        this.gameAction = gameAction;
    }
}
