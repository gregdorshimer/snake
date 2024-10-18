import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInputListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text == "Up" || text == "Down" || text == "Left" || text == "Right") {
            Main.directSnake(text);
        }
    }
}
