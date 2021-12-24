public class Game {
    private final Snake snake = new Snake();
    private final UIBuilder uiBuilder = new UIBuilder();

    public void runGame() {
        uiBuilder.createFrame(snake);
        try {
            while (snake.snakePoints.size() != UIBuilder.numberOfSquares) {
                if (snake.didCollide()) {
                    Util.showGameEndDialog(UIBuilder.frame, "You hit yourself, play again?");
                }
                uiBuilder.updateGrid(snake.getApplePoint(), snake.snakePoints);
                uiBuilder.setFrame();
                Thread.sleep(125);
                if (snake.getSnakeHeadPoint().equals(snake.getApplePoint())) {
                    snake.eatApple(uiBuilder.getPanelGrid());
                }
                snake.moveBody();
            }
        } catch (Exception e) {
            Util.showGameEndDialog(UIBuilder.frame, "You hit the border, play again?");
        }

        Util.showGameEndDialog(UIBuilder.frame, "You Won, play again?");
    }
}
