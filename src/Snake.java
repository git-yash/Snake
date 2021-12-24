import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private int currentDirection;
    public final ArrayList<Point> snakePoints;

    private final Point snakeHeadPoint;
    private final Point applePoint;

    public Snake() {
        this.currentDirection = KeyEvent.VK_RIGHT;
        this.snakePoints = new ArrayList<>();

        this.snakeHeadPoint = new Point(7, 1);
        this.applePoint = new Point(7, 12);
    }

    public Point getApplePoint() {
        return this.applePoint;
    }

    public void changeSnakeDirection(int keyValue) {
        this.currentDirection = keyValue;
    }

    public boolean shouldAllowGoingUp(int key) {
        return key == KeyEvent.VK_UP && this.currentDirection != KeyEvent.VK_DOWN;
    }

    public boolean shouldAllowGoingRight(int key) {
        return key == KeyEvent.VK_RIGHT && this.currentDirection != KeyEvent.VK_LEFT;
    }

    public boolean shouldAllowGoingDown(int key) {
        return key == KeyEvent.VK_DOWN && this.currentDirection != KeyEvent.VK_UP;
    }

    public boolean shouldAllowGoingLeft(int key) {
        return key == KeyEvent.VK_LEFT && this.currentDirection != KeyEvent.VK_RIGHT;
    }

    public Point getSnakeHeadPoint() {
        return this.snakeHeadPoint;
    }

    public boolean didCollide() {
        for (int i = 1; i < snakePoints.size(); i++) {
            Point snakePoint = snakePoints.get(i);
            if (snakePoint.equals(snakeHeadPoint)) {
                return true;
            }
        }

        return false;
    }

    private Point getValidApplePoint(JPanel[][] panelGrid) {
        ArrayList<Point> validPoints = new ArrayList<>();

        for (int i = 0; i < UIBuilder.numberOfRows; i++) {
            for (int j = 0; j < UIBuilder.numberOfColumns; j++) {
                if (panelGrid[i][j].getBackground() == Color.black) {
                    validPoints.add(new Point(i, j));
                }
            }
        }

        int randomPointIndex = (int) (Math.random() * validPoints.size());
        return validPoints.get(randomPointIndex);
    }

    public void eatApple(JPanel[][] panelGrid, UIBuilder uiBuilder) {
        uiBuilder.incrementScore();

        Point validApplePoint = getValidApplePoint(panelGrid);
        applePoint.x = validApplePoint.x;
        applePoint.y = validApplePoint.y;
        Point snakeTailPoint = snakePoints.get(0);

        switch (this.currentDirection) {
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

    public void moveHead() {
        switch (this.currentDirection) {
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

    private Point[] getPreviousBody() {
        Point[] previousBody = new Point[snakePoints.size()];
        for (int i = 0; i < previousBody.length; i++) {
            previousBody[i] = new Point(snakePoints.get(i).x, snakePoints.get(i).y);
        }

        return previousBody;
    }

    public void moveBody() {
        Point[] previousBody = getPreviousBody();
        moveHead();

        for (int i = 1; i < snakePoints.size(); i++) {
            snakePoints.set(i, previousBody[i - 1]);
        }
    }
}