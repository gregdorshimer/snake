import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInputListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text.equals("Up") || text.equals("Down") || text.equals("Left") || text.equals("Right")) {
            Main.directSnake(text);
        }
    }
}
