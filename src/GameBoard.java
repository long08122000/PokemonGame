import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameBoard extends JPanel {
    private ArrayList<File> listGameCard;
    public Integer SCORE = 0;
    private ArrayList<GameCard> cardSelected = new ArrayList<>();

    private GameController controller;

    public GameBoard() {
        initSetting();
        initGameCard();
    }

    public int getScore() {
        return SCORE;
    }

    public void setSCORE(Integer SCORE) {
        this.SCORE = SCORE;
    }

    private void initSetting() {
        setLayout(new GridLayout(8, 16, 3, 3));
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(Main.WIDTH - 60, Main.HEIGHT - 180));
    }

/// test branch
    public void initGameCard() {
        getListGameCard();
        for (int i = 0; i < listGameCard.size(); i++) {
            GameCard gameCard = new GameCard();
            gameCard.setName(listGameCard.get(i).getName());
            gameCard.setOpaque(true);
            gameCard.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    boolean isDropped = gameCard.isDrop();
                    if (isDropped) {
                        return;
                    }
                    boolean isSelected = !gameCard.isSelected();
                    //  int selectedSize = cardSelected.size();
                    gameCard.setSelected(isSelected);
                    if (isSelected) {
                        cardSelected.add(gameCard);
                    } else {
                        cardSelected.remove(gameCard);
                    }

                    if (cardSelected.size() == 2) {
                        GameCard card1 = cardSelected.get(0);
                        GameCard card2 = cardSelected.get(1);
                        if (card1.getName().equals(card2.getName())) {
                            card1.setBackground(Color.LIGHT_GRAY);
                            card2.setBackground(Color.LIGHT_GRAY);
                            card1.drop();
                            card2.drop();
                            cardSelected.clear();
                            SCORE += 10;
                            controller.fixScore(SCORE);

                        } else {
                            card1.setSelected(false);
                            card2.setSelected(false);
                            cardSelected.clear();
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            gameCard.setBackground(Color.GRAY);
            gameCard.setHorizontalAlignment(SwingConstants.CENTER);
            gameCard.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon imageIcon = new ImageIcon(listGameCard.get(i).getPath());
            Image newImage = imageIcon.getImage().getScaledInstance(45, 50, Image.SCALE_SMOOTH);
            gameCard.setIcon(new ImageIcon(newImage));
            add(gameCard);
        }
    }

    private void getListGameCard() {
        File resource = new File("resources");
        File[] image = resource.listFiles();
        listGameCard = new ArrayList<>(Arrays.asList(image));
        listGameCard.addAll(Arrays.asList(image));
        Collections.shuffle(listGameCard);
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
