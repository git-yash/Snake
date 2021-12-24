import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UIBuilder {
    public static final int numberOfRows = 15;
    public static final int numberOfColumns = 15;
    public static final int numberOfSquares = numberOfColumns * numberOfRows;
    public static final JFrame frame = new JFrame("Snake");
    public static final JPanel gamePanel = new JPanel();
    public static final JPanel scoresPanel = new JPanel();
    private final JPanel[][] panelGrid = new JPanel[numberOfRows][numberOfColumns];
    private int currentScore;
    private final JTextField scoreTextField = new JTextField("Score: " + currentScore);
    private final JTextField highScoreTextField = new JTextField("High Score: " + Util.getHighScore());

    public UIBuilder() throws FileNotFoundException {
        scoresPanel.removeAll();
        currentScore = 0;
    }

    public JPanel[][] getPanelGrid() {
        return this.panelGrid;
    }

    public void incrementScore() throws IOException {
        currentScore++;
        scoreTextField.setText("Score: " + currentScore);

        if (currentScore > Util.getHighScore()) {
            Util.setHighScore(currentScore);
            highScoreTextField.setText("High Score: " + Util.getHighScore());
        }
    }

    public void createFrame(Snake snake) {
        snake.snakePoints.add(snake.getSnakeHeadPoint());
        snake.snakePoints.add(new Point(7, 2));
        gamePanel.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        scoresPanel.setBackground(Color.black);
        scoreTextField.setBackground(Color.black);
        highScoreTextField.setBackground(Color.black);
        scoreTextField.setForeground(Color.white);
        highScoreTextField.setForeground(Color.white);
        scoreTextField.setEditable(false);
        highScoreTextField.setEditable(false);
        scoresPanel.add(scoreTextField);
        scoresPanel.add(highScoreTextField);

        frame.setVisible(true);
        frame.setSize(500, 550);
        frame.setLayout(new BorderLayout());
        frame.add(scoresPanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new GameKeyListener(snake));

        Border scoresPanelBorder = BorderFactory.createLineBorder(Color.white);
        scoresPanel.setBorder(scoresPanelBorder);

    }

    private void addSnakePoints(ArrayList<Point> snakePoints) {
        for (Point snakePoint : snakePoints) {
            panelGrid[snakePoint.x][snakePoint.y].setBackground(Color.green);
        }
    }

    public void updateGrid(Point applePoint, ArrayList<Point> snakePoints) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.black);
                panelGrid[i][j] = panel;
            }
        }
        panelGrid[applePoint.x][applePoint.y].setBackground(Color.red);
        addSnakePoints(snakePoints);
    }

    public void setGamePanel() {
        gamePanel.removeAll();
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                gamePanel.add(panelGrid[i][j]);
            }
        }
        gamePanel.revalidate();
    }

}