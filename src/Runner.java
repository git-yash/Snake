import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Runner {
    private final static JFrame frame = new JFrame("Snake");
    private static final JPanel[][] panelGrid = new JPanel[15][15];
    private static final ArrayList<Point> snakePoints = new ArrayList<>();
    private static final Point snakeHeadPoint = new Point(7, 1);
    private static final Point applePoint = new Point(7, 12);
    private static int currentDirection = KeyEvent.VK_RIGHT;

    public static boolean didCollide() {
        for (int i = 1; i < snakePoints.size(); i++) {
            Point snakePoint = snakePoints.get(i);
            if (snakePoint.equals(snakeHeadPoint)) {
                return true;
            }
        }

        return false;
    }

    public static void eatApple() {
        applePoint.x = (int) (Math.random() * 15);
        applePoint.y = (int) (Math.random() * 15);
        Point snakeTailPoint = snakePoints.get(0);
        switch (currentDirection) {
            case KeyEvent.VK_UP:
                snakePoints.add(new Point(snakeTailPoint.x + 1, snakeTailPoint.y));
                break;
            case KeyEvent.VK_RIGHT:
                snakePoints.add(new Point(snakeTailPoint.x, snakeTailPoint.y - 1));
                break;
            case KeyEvent.VK_DOWN:
                snakePoints.add(new Point(snakeTailPoint.x - 1, snakeTailPoint.y));
                break;
            case KeyEvent.VK_LEFT:
                snakePoints.add(new Point(snakeTailPoint.x, snakeTailPoint.y + 1));
                break;
        }
    }

    public static void moveHead() {
        switch (currentDirection) {
            case KeyEvent.VK_UP:
                snakeHeadPoint.x--;
                break;
            case KeyEvent.VK_RIGHT:
                snakeHeadPoint.y++;
                break;
            case KeyEvent.VK_DOWN:
                snakeHeadPoint.x++;
                break;
            case KeyEvent.VK_LEFT:
                snakeHeadPoint.y--;
                break;
        }
    }

    private static Point[] getPreviousBody() {
        Point[] previousBody = new Point[snakePoints.size()];
        for (int i = 0; i < previousBody.length; i++) {
            previousBody[i] = new Point(snakePoints.get(i).x, snakePoints.get(i).y);
        }

        return previousBody;
    }

    public static void moveBody() {
        Point[] previousBody = getPreviousBody();
        moveHead();

        for (int i = 1; i < snakePoints.size(); i++) {
            snakePoints.set(i, previousBody[i - 1]);
        }
    }

    public static void addSnakePoints() {
        for (Point snakePoint : snakePoints) {
            panelGrid[snakePoint.x][snakePoint.y].setBackground(Color.blue);
        }
    }

    public static void setFrame() {
        frame.getContentPane().removeAll();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                frame.add(panelGrid[i][j]);
            }
        }
        frame.repaint();
        frame.revalidate();
    }

    public static void updateGrid() {
        boolean isLight = true;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                JPanel panel = new JPanel();

                if (isLight) {
                    panel.setBackground(Color.lightGray);
                } else {
                    panel.setBackground(Color.gray);
                }

                panelGrid[i][j] = panel;
                isLight = !isLight;

            }
        }
        panelGrid[applePoint.x][applePoint.y].setBackground(Color.red);
        addSnakePoints();
    }

    public static void main(String[] args) {
        //add points
        snakePoints.add(snakeHeadPoint);
        snakePoints.add(new Point(7, 2));
        Point snakeHeadPoint = snakePoints.get(0);

        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();

                if (key == KeyEvent.VK_UP && currentDirection != KeyEvent.VK_DOWN) {
                    currentDirection = KeyEvent.VK_UP;
                } else if (key == KeyEvent.VK_RIGHT && currentDirection != KeyEvent.VK_LEFT) {
                    currentDirection = KeyEvent.VK_RIGHT;
                } else if (key == KeyEvent.VK_DOWN && currentDirection != KeyEvent.VK_UP) {
                    currentDirection = KeyEvent.VK_DOWN;
                } else if (key == KeyEvent.VK_LEFT && currentDirection != KeyEvent.VK_RIGHT) {
                    currentDirection = KeyEvent.VK_LEFT;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(15, 15));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            while (snakePoints.size() != 225) {
                if (didCollide()) {
                    JOptionPane.showMessageDialog(null, "Game Over");
                    System.exit(0);
                }
                updateGrid();
                setFrame();
                Thread.sleep(150);
                if (snakeHeadPoint.equals(applePoint)) {
                    eatApple();
                }
                moveBody();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Game Over");
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null, "You Won");
        System.exit(0);
    }
}