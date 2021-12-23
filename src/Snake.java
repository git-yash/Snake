import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    public final ArrayList<Point> snakePoints = new ArrayList<>();
    private final Point snakeHeadPoint = new Point(7, 1);
    private final Point applePoint = new Point(7, 12);
    public static int currentDirection = KeyEvent.VK_RIGHT;

    public Point getApplePoint() {
        return this.applePoint;
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

    public void eatApple() {
        applePoint.x = (int) (Math.random() * UIBuilder.numberOfRows);
        applePoint.y = (int) (Math.random() * UIBuilder.numberOfColumns);
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

    public void moveHead() {
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