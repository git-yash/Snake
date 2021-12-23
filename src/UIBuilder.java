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

    public void createFrame(Snake snake) {
        snake.snakePoints.add(snake.getSnakeHeadPoint());
        snake.snakePoints.add(new Point(7, 2));

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();

                if (key == KeyEvent.VK_UP && Snake.currentDirection != KeyEvent.VK_DOWN) {
                    Snake.currentDirection = KeyEvent.VK_UP;
                } else if (key == KeyEvent.VK_RIGHT && Snake.currentDirection != KeyEvent.VK_LEFT) {
                    Snake.currentDirection = KeyEvent.VK_RIGHT;
                } else if (key == KeyEvent.VK_DOWN && Snake.currentDirection != KeyEvent.VK_UP) {
                    Snake.currentDirection = KeyEvent.VK_DOWN;
                } else if (key == KeyEvent.VK_LEFT && Snake.currentDirection != KeyEvent.VK_RIGHT) {
                    Snake.currentDirection = KeyEvent.VK_LEFT;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
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