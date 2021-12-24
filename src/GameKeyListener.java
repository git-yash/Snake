import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final Snake snake;

    public GameKeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        if (this.snake.shouldAllowGoingUp(key)) {
            this.snake.changeSnakeDirection(KeyEvent.VK_UP);
        } else if (this.snake.shouldAllowGoingRight(key)) {
            this.snake.changeSnakeDirection(KeyEvent.VK_RIGHT);
        } else if (this.snake.shouldAllowGoingDown(key)) {
            this.snake.changeSnakeDirection(KeyEvent.VK_DOWN);
        } else if (this.snake.shouldAllowGoingLeft(key)) {
            this.snake.changeSnakeDirection(KeyEvent.VK_LEFT);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // do nothing
    }
}
