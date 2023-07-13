import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 700;
    private static JFrame frame = new JFrame("Pokemon");
    public static JPanel mainPanel = new JPanel();
    private static GameBoard gameBoard;
    private static GameController gameController;

    public static void main(String[] args) {
        gameBoard = new GameBoard();
        gameController = new GameController(gameBoard); // case 1: use constructor
        gameBoard.setController(gameController); //case 2: use setter getter
        initSetting();
        addGameController();
        addGameBoard();
        frame.add(mainPanel);
    }

    private static void initSetting() {
        URL url_icon_jframe = Main.class.getResource("Gartoon.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url_icon_jframe);

        frame.setIconImage(img);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    private static void addGameController() {
//        controlPanel.addAction(new SetListener() {
//            @Override
//            public void rePlay() {
//                gameBoard.removeAll();
//                gameBoard.revalidate();
//                gameBoard.repaint();
//            }
//        });
        mainPanel.add(gameController);
    }

    public static void addGameBoard() {
        mainPanel.add(gameBoard);
    }
}