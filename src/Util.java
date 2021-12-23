import javax.swing.*;
import java.awt.event.KeyEvent;

public class Util {
    public static void showGameEndDialog(JFrame frame, String message) {
        int playerChoice = JOptionPane.showConfirmDialog(frame, message, "Game End",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (playerChoice == JOptionPane.YES_OPTION) {
            frame.dispose();
            Snake.currentDirection = KeyEvent.VK_RIGHT;
            Game game = new Game();
            game.runGame();
        } else {
            System.exit(0);
        }
    }
}