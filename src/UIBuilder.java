import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class UIBuilder {
    public static final int numberOfRows = 15;
    public static final int numberOfColumns = 15;
    public static final int numberOfSquares = numberOfColumns * numberOfRows;
    public static final JFrame frame = new JFrame("Snake");
    private final JPanel[][] panelGrid = new JPanel[numberOfRows][numberOfColumns];

    public JPanel[][] getPanelGrid() {
        return this.panelGrid;
    }

    public void createFrame(Snake snake) {
        snake.snakePoints.add(snake.getSnakeHeadPoint());
        snake.snakePoints.add(new Point(7, 2));

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new GameKeyListener(snake));
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

    public void setFrame() {
        frame.getContentPane().removeAll();
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                frame.add(panelGrid[i][j]);
            }
        }
        frame.repaint();
        frame.revalidate();
    }

}