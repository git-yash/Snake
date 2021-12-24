import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
    public static void showGameEndDialog(JFrame frame, String message) throws FileNotFoundException {
        int playerChoice = JOptionPane.showConfirmDialog(frame, message, "Game End",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (playerChoice == JOptionPane.YES_OPTION) {
            frame.dispose();
            Game game = new Game();
            game.runGame();
        } else {
            System.exit(0);
        }
    }

    public static int getHighScore() throws FileNotFoundException {
        Scanner kb = new Scanner(new File("highScore.txt"));
        return kb.nextInt();
    }

    public static void setHighScore(int newHighScore) throws IOException {
        FileWriter fw = new FileWriter("highScore.txt");
        fw.write(String.valueOf(newHighScore));
        fw.close();
    }
}