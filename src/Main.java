import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;

// TODO troubleshoot window display size
// TODO create game-over screen
// TODO sounds
// TODO allow choosing a level when calling Main.main() i.e. pass level as String[] args

public class Main extends JFrame {
    // game constants
    private static final int LEVEL = 3;
    static final int BALL_RAD = 5;
    static final int FRAME_SIZE = 410;
    static final int SNAKE_COLOR = 0x9F2424; // RGB -> 165,42,42
    static final int APPLE_COLOR = 0x41B324; // RGB -> 255,0,0
    static final Color red = new Color(SNAKE_COLOR);
    static final Color green = new Color(APPLE_COLOR);
    private static final int TICK_TIME = 800 / LEVEL;
    private static final int STARTING_LIVES = max(0, 4 - LEVEL);

    // game data
    static ArrayList<Ball> snake;
    static Ball apple;
    static int lives;
    static int apples;
    static JFrame frame;


    public static void main(String[] args) {

        // initialize game data
        snake = initSnake();
        apple = genApple(snake);
        apples = 0;
        lives = STARTING_LIVES;

        // initialize display, input listener
        frame = createWindow(FRAME_SIZE);


        // begin game loop
        long time = System.currentTimeMillis();
        boolean gameOver = false;
        while (!gameOver) {
            if ((System.currentTimeMillis() - time) > TICK_TIME) {
                moveBalls(snake);
                frame.repaint();
                if (outOfBounds(snake) || selfEat(snake)) {
                    loseLife();
                    // System.out.println("lost a life");
                }
                if (lives < 0) {
                    gameOver = true;
                }
                if (gotApple()) {
                    apple = genApple(snake);
                    growSnake(snake);
                    apples++;
                }
                time = System.currentTimeMillis();
            }
        }
        gameOver();
    }

    public static JFrame createWindow(int size) {
        JFrame frame = new JFrame("Snake"); // set window title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate java program when window is closed
        frame.addKeyListener(new UserInputListener()); // add KeyListener to the JFrame to capture key input
        frame.setLocationRelativeTo(null); // make window appear in the middle of the screen
        frame.setSize(size, size); // define dimensions of frame
        frame.setPreferredSize(new Dimension(size, size));
        frame.setMinimumSize(new Dimension(size, size));
        frame.setMaximumSize(new Dimension(size, size));
        frame.setVisible(true); // display frame

        SnakePanel panel = new SnakePanel();
        panel.setOpaque(false);
        frame.add(panel);

        return frame;
    }

    static class SnakePanel extends JPanel {
        public SnakePanel() {
            super();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(green);
            g.fillOval(apple.x - BALL_RAD, apple.y - BALL_RAD, BALL_RAD * 2, BALL_RAD * 2);
            g.setColor(red);
            for (Ball segment : snake) {
                g.fillOval(segment.x - BALL_RAD, segment.y - BALL_RAD, BALL_RAD * 2, BALL_RAD * 2);
            }
        }
    }

    static ArrayList<Ball> initSnake() {
        // TODO consider starting with 3 segments
        ArrayList<Ball> list = new ArrayList<Ball>();
        list.add(new Ball("snake", SNAKE_COLOR, FRAME_SIZE / 2, FRAME_SIZE / 2, ""));
        // System.out.println("snake: (" + list.getFirst().x + ", " + list.getFirst().y + ")");
        return list;
    }

    // generates a new apple with random coordinates in the window
    static Ball genApple(ArrayList<Ball> snake) {
        while (true) {
            int x = (int)(Math.round(Math.random() * FRAME_SIZE));
            x = x - (x % (2 * BALL_RAD)) + BALL_RAD;
            int y = (int)(Math.round(Math.random() * FRAME_SIZE));
            y = y - (y % (2 * BALL_RAD)) + BALL_RAD;
            // System.out.println("apple: (" + x + ", " + y + ")");
            Ball newApple = Ball.makeApple(x, y);
            if (!overlapList(newApple, snake)) {
                return newApple;
            }
        }
    }

    // returns whether the two given balls overlap
    static boolean overlap(Ball ball1, Ball ball2) {
        return Math.abs(ball1.x - ball2.x) < (2 * BALL_RAD) && Math.abs(ball1.y - ball2.y) < (2 * BALL_RAD);
    }

    // returns whether the given ball overlaps with any of the balls in given list of balls
    static boolean overlapList(Ball ball, ArrayList<Ball> balls) {
        for (Ball segment : balls) {
            if (overlap(ball, segment)) {
                return true;
            }
        }
        return false;
    }

    // moves a list of balls according to each ball's direction
    static void moveBalls(ArrayList<Ball> balls) {
        for (Ball ball : balls) {
            String dir = ball.dir;
            // move ball
            switch (dir) {
                case "up" -> ball.y -= 2 * BALL_RAD;
                case "down" -> ball.y += 2 * BALL_RAD;
                case "left" -> ball.x -= 2 * BALL_RAD;
                case "right" -> ball.x += 2 * BALL_RAD;
            }
        }
        for (int i = balls.size() - 1; i > 0; i--) {
            balls.get(i).dir = balls.get(i -1).dir;
        }
    }

    // generates a segment to be added to the end of the snake
    static void growSnake(ArrayList<Ball> balls) {
        int x = balls.getLast().x;
        int y = balls.getLast().y;
        switch(balls.getLast().dir) {
            case "up" -> y += 2 * BALL_RAD;
            case "down" -> y -= 2 * BALL_RAD;
            case "left" -> x += 2 * BALL_RAD;
            default -> x -= 2 * BALL_RAD;
        }
        balls.addLast(new Ball("snake", SNAKE_COLOR, x, y, balls.getLast().dir));
    }

    // returns if the snake caught the apple
    static boolean gotApple() {
        return overlapList(apple, snake);
    }

    // checks if the snake is eating itself
    static boolean selfEat(ArrayList<Ball> balls) {
        ArrayList<Ball> restOfSnake = new ArrayList<Ball>(balls.subList(1, balls.size()));
        return overlapList(balls.getFirst(), restOfSnake);
    }

    // returns if the snake is running off the screen
    static boolean outOfBounds(ArrayList<Ball> balls) {
        // only have to check first segment because subsequent segments follow the first's path
        if (balls.getFirst().x < BALL_RAD ||
            balls.getFirst().x > (FRAME_SIZE - BALL_RAD) ||
            balls.getFirst().y < BALL_RAD ||
            balls.getFirst().y > (FRAME_SIZE - BALL_RAD)) {
            System.out.println("out of bounds: (" + balls.getFirst().x + ", " + balls.getFirst().y + ")");
            return true;
        } else { return false; }
    }

    // events for when a player loses a life
    static void loseLife() {
        lives--;
        snake = initSnake();
        apple = genApple(snake);
    }

    // events for when player runs out of lives
    static void gameOver() {
        // TODO show screen with number of apples collected and level
        System.out.println("gameOver();");
    }

    static void directSnake(String s) {
        snake.getFirst().dir = s.toLowerCase();
    }
}